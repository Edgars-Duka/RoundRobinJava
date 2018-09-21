/*
 Name:Edgars Duka
 ID:R00103479
 Project: 01

 */

import java.util.Scanner;        //import methods

public class Round_Robin_Football_Tournament {

    public static void main(String[] args) {
        // Print Banner
        System.out.println("****************************************************");
        System.out.println("* Welcome to CIT's Round Robin Football Tournament *");
        System.out.println("****************************************************");

        Scanner keyboard = new Scanner(System.in); // declare variables
        char repeat = 'Y';
        int teams;

        // int matchCount;

        int totalMatchesCounter = 0; // counts the total amount of unique matches
        int currentTeamsMatchCounter; // counts how many matches the team played
        boolean firstTimePlaying;
        // Do While loop to re-enter details
        do {

            // Loop to check for valid input for team numbers
            do {
                System.out.print("Enter the number of teams: ");

                // validating Int var type
                while (!keyboard.hasNextInt()) {
                    keyboard.nextLine();
                    System.out.print("Please enter an Interger value for the numbner of teams:");
                }

                teams = keyboard.nextInt();
                // test for input error
                if (teams < 2)
                    System.out.println("There must be at least 2 teams.");
            } while (teams < 2);

            int currentTeam = 1, playingAgainst = 1, result, winError = 0, looseError = 0, drawError = 0,
                    attendance = 0, numMatches, winPoints = 0, teamsDraw = 1;

            final double pot;
            String input;
            String teamResults = "", teamWin = "";

            // loop for results input
            do {
                String teamName = "";
                keyboard.nextLine();
                System.out.print("what is the name of team " + currentTeam + ": ");
                teamName = keyboard.nextLine();
                /* teamName = Input.charAt(0); */
                int win = 0, loose = 0, draw = 0, points = 0;

                currentTeamsMatchCounter = 0; // reset the counter

                playingAgainst = 1; // the current team will start checking/playing from 1 again

                do {
                    // prompting for correct inputs and checking for relevant
                    // inputs, while incrementing certain variables.

                    /*
                     * Checks if the team we're playing against now is us...
                     * i.e team 1 vs team 1 will NOT play because its the same team
                     */
                    if (currentTeam != playingAgainst) {
                        firstTimePlaying = false; // reset the first time playing boolean

                        currentTeamsMatchCounter++;

                        /*
                         * Checks if the teams played against eachother before,
                         * or if it's just a "carefully reenter" job
                         */
                        if (currentTeam < playingAgainst) {
                            // they NEVER played against eachother before
                            totalMatchesCounter++;
                            firstTimePlaying = true;
                        }

                        if (firstTimePlaying) {
                            System.out.println("Match " + totalMatchesCounter + ": (" + currentTeamsMatchCounter
                                    + " of " + (teams - 1) + ") for " + teamName + " vs. team " + playingAgainst);
                        } else {
                            System.out.println(
                                    "Carefully reenter the score for: " + teamName + " vs. team " + playingAgainst);

                        }

                        System.out.println("Did " + teamName + "?");
                        System.out.println("\t1: Draw");
                        System.out.println("\t2: Win");
                        System.out.println("\t3: Loose");
                        System.out.print("==> ");
                        result = keyboard.nextInt();
                        if (result == 1) {
                            draw++;
                            points += 1;
                            drawError++;
                        } else if (result == 2) {
                            win++;
                            points += 3;
                            winError++;
                        } else if (result == 3) {
                            loose++;
                            looseError++;
                        } else {
                            System.out.println("Invalid match result, please enter result again.");
                            break;
                        }

                        if (firstTimePlaying) {
                            // prompt for attendance figures
                            // we only check if it's their first time playing against eachother

                            System.out.print("Match " + totalMatchesCounter + ": (" + currentTeamsMatchCounter + " of "
                                    + (teams - 1) + ") for " + teamName + " vs. team " + playingAgainst
                                    + " how many suporters attended? ");
                            attendance += keyboard.nextInt();
                        }
                    }
                    playingAgainst++; // increment this at the end, not in the switch statement

                } while (playingAgainst <= teams);


                currentTeam++;
                // checking for a winning team or tie winning teams
                if (winPoints < points) {
                    winPoints = points;
                    teamWin = teamName;
                    teamsDraw = 1;
                } else if (winPoints == points) {
                    teamWin += " and " + teamName;
                    teamsDraw++;
                }

                // concatonating results into a single string as each loop finishes for a team.
                teamResults += (teamName + "\t" + draw + "\t" + win + "\t" + loose + "\t" + points + "\n");

            } while (currentTeam <= teams);

            // checking that the match result add up(same number of wins and losses and equal number of draws)
            if (winError == looseError && (drawError % 2) == 0) {

                // print table
                System.out.println("Team\tDraw\tWin\tLost\tTotal");
                System.out.println("====\t====\t====\t====\t=====");
                System.out.println(teamResults);

                System.out.println("Total attendance at all matches: " + attendance);

                numMatches = (teams * (teams - 1)) / 2;

                System.out.println("Average attendance per game is: " + attendance / numMatches);

                pot = attendance * 5.5;

                System.out.println("Total takings at all matches is (�): " + pot);
                // switch to decide which grammar to use for the winning
                // team/teams
                switch (teamsDraw) {

                    case 1:
                        System.out.print("The winning team, " + teamWin + ", takes home " + pot);
                        break;
                    default:
                        System.out.print("The winning teams, " + teamWin + " take home " + (pot / teamsDraw) + " each.");
                        break;
                }

                // print statements and prompt for incorrect results.
            } else {
                keyboard.nextLine();
                System.out.print("The result appear to be incorrect, would you like to re-enter the results? (Y/N): ");
                input = keyboard.nextLine();
                repeat = input.charAt(0);
            }

        } while (repeat == 'Y');

        keyboard.close();
    }

}