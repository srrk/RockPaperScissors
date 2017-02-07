/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zeroadmin
 */
public class RockPaperScissorsServlet extends HttpServlet {

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // clear history
        if(request.getParameter("history").equals("clear")){
            HttpSession session = request.getSession();
            ResultHistory resulthistory = (ResultHistory) session.getAttribute("result");
            session.setAttribute("result", null);
            response.sendRedirect("display.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Random system option (Rock/Paper/Scissor);
        String playerPick = pickRandomly();
        
        // decideTheWinnerAndForward
        decideTheWinnerAndForward(request,response,playerPick);
        
    }
    
    public void decideTheWinnerAndForward(HttpServletRequest request, HttpServletResponse response,
            String playerPick) throws IOException{
        
        // Process the choice.
        String userPick = request.getParameter("pick");
        HttpSession session = request.getSession();
        String winner;
        
        if(userPick.equals(playerPick)){
            winner="tie";
        }
        else if(userPick.equals("rock") && playerPick.equals("scissors")){
            winner="user";
        }
        else if(userPick.equals("scissors") && playerPick.equals("rock")){
            winner="system";
        }
        else if(userPick.equals("scissors") && playerPick.equals("paper")){
            winner = "user";
        }
        else if(userPick.equals("paper") && playerPick.equals("scissors")){
            winner = "system";
        }
        else if(userPick.equals("paper") && playerPick.equals("rock")){
            winner = "user";
        }
        else if(userPick.equals("rock") && playerPick.equals("paper")){
            winner = "system";
        }
        else {
            winner = "none";
        }
        
        // Record History of Records.
        recordGameHistory(session,userPick,playerPick,winner);
           
        
        // Redirect the output to JSP template.
        session.setAttribute("winner", winner);
        response.sendRedirect("display.jsp");
        
    }
    
    private void recordGameHistory(HttpSession session, String userPick, String playerPick, String winner){
       // check already a session is available.
       ResultHistory resulthistory = new ResultHistory();
       
       if(session.getAttribute("result") != null){
           ResultHistory existingHistory = (ResultHistory) session.getAttribute("result");
           // copy existing records first.
           for(Result result : existingHistory.getResults()){
               resulthistory.addResult(result);
           }
       
       }
       
        resulthistory.printRecords(); // debug.
       
        // copy latest record into history.
        resulthistory.addRecord(winner, userPick, playerPick);
        session.setAttribute("result", resulthistory);
       
    }
    
    private String pickRandomly(){
        
        //1. Make a 'random' choice using Random in range of Rock(0), Paper(1), Scissors(2).
        Random random = new Random();
        int choice = random.nextInt(3);
        
        //2. corelate the choice.
        if(choice == 0){
           return "rock";
        }
        else if(choice == 1){
            return "paper";
        }
        else{
            return "scissors";
        }
        
    }
    
    

}
