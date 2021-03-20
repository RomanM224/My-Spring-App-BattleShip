package com.maistruks.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.maistruks.portfolio.battleShip.model.Field;
import com.maistruks.portfolio.battleShip.model.Fleet;
import com.maistruks.portfolio.battleShip.service.ComputerAI;
import com.maistruks.portfolio.battleShip.service.FieldChecker;
import com.maistruks.portfolio.battleShip.service.GamaManager;
import com.maistruks.portfolio.battleShip.service.ShipsGenerator;
import com.maistruks.portfolio.battleShip.service.TableCreater;
import com.maistruks.portfolio.battleShip.service.UserInputChecker;
import com.maistruks.portfolio.exception.BattleShipException;


@Controller
@RequestMapping(value = "/battleShip")
public class BattleshipController {
    
    @Autowired
    private GamaManager gameManager;
    
    @Autowired
    private FieldChecker fieldChecker;
    
    @Autowired
    private TableCreater tableCreater;
    
    @Autowired
    private ShipsGenerator shipsGenerator;
    
    @Autowired
    private UserInputChecker userInputChecker;

    @GetMapping("/startGame")
    public ModelAndView startGame(HttpSession session) {
        session.removeAttribute("exception");
        session.setAttribute("fieldChecker", fieldChecker);
        session.setAttribute("tableCreater", tableCreater);
        ModelAndView mav = new ModelAndView("battleShip/startGame");
        return mav;
    }

    @PostMapping("/startGame")
    public ModelAndView startGame(HttpSession session, HttpServletRequest request) {
        session.removeAttribute("exception");
        List<Field> myFields = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "my_" + shipsGenerator.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                myFields.add(field);
                System.out.println(field);
            }
        }
        List<Field> enemyShots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "enemyShot_" + shipsGenerator.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                enemyShots.add(field);
            }
        }
        try {
            userInputChecker.checkInput(myFields);
        } catch (BattleShipException exception) {
            session.setAttribute("exception", exception.getMessage());
            return new ModelAndView("battleShip/startGame");
        }
        Fleet myFleet = gameManager.generateFleet(myFields);
        session.setAttribute("enemyShots", enemyShots);
        session.setAttribute("myShipsFields", gameManager.generateFleet(myFields).getAllFields());
        session.setAttribute("myFleet", myFleet);
        
        List<Field> myShots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "myShot_" + shipsGenerator.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                myShots.add(field);
            }
        }
        List<Field> enemyShipsFields = shipsGenerator.generateShips();
        Fleet enemyFleet = gameManager.generateFleet(enemyShipsFields);
        session.setAttribute("myShots", myShots);
        session.setAttribute("enemyShipsFields", enemyShipsFields);
        session.setAttribute("enemyFleet", enemyFleet);
        
        ComputerAI computerAI = new ComputerAI();
        session.setAttribute("computerAI", computerAI);

        ModelAndView mav = new ModelAndView("battleShip/game");
        return mav;
    }
    
    @PostMapping("/game")
    public ModelAndView game(HttpSession session, HttpServletRequest request) {
        List<Field> myShots = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                String parameter = "myShot_" + shipsGenerator.getLatter(i) + j;
                Boolean value;
                if(request.getParameter(parameter) != null) {
                    value = true;
                } else {
                    value = false;
                }
                Field field = new Field(parameter, value);
                myShots.add(field);
            }
        }
        Fleet myFleet =(Fleet) session.getAttribute("myFleet");
        ComputerAI computerAI = (ComputerAI) session.getAttribute("computerAI");
        myFleet.destroyMyShip(computerAI.getEnemyShots());
        session.setAttribute("enemyShots", computerAI.getEnemyShots());
        computerAI.enemyHit();
        
        Fleet enemyFleet =(Fleet) session.getAttribute("enemyFleet");
        enemyFleet.destroyEnemyShip(myShots);
        session.setAttribute("myShots", myShots);
        
        session.setAttribute("myFleet", myFleet);
        session.setAttribute("enemyFleet", enemyFleet);
        session.setAttribute("computerAI", computerAI);
        if(enemyFleet.getShips().size() == 0) {
            session.setAttribute("info", "You Win!");
            return new ModelAndView("battleShip/finish");
        }
        if(myFleet.getShips().size() == 0) {
            session.setAttribute("info", "You Lose!");
            return new ModelAndView("battleShip/finish");
        }
        return new ModelAndView("battleShip/game");
    }
}
