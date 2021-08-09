import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //Url for hero list
        get("/heroes", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Hero> heroes = Hero.getAll();
            model.put("heroes", heroes);
            return new ModelAndView(model, "heroList.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new hero form
        get("/heroes/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "heroForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/heroes/new", (req, res) -> { //URL to make new hero on POST route
            Map<String, Object> model = new HashMap<>();

            String name = req.queryParams("name");
            String power = req.queryParams("power");
            String weakness = req.queryParams("weakness");
            int age = Integer.parseInt(req.queryParams("age"));

            Hero newHero = new Hero(name,age,weakness,power);
            model.put("hero", newHero);

            return new ModelAndView(model, "heroSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual hero
        get("/heroes/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Hero foundHero = Hero.findById(idOfHeroToFind); //use it to find post
            model.put("hero", foundHero); //add it to model for template to display
            return new ModelAndView(model, "heroDetail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());


        //get: show a form to update a hero
        get("/heroes/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            model.put("editHero", editHero);
            return new ModelAndView(model, "heroForm.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a hero
        post("/heroes/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int idOfHeroToEdit = Integer.parseInt(req.params("id"));
            Hero editHero = Hero.findById(idOfHeroToEdit);
            editHero.updateName(newName);
            return new ModelAndView(model, "heroSuccess.hbs");
        }, new HandlebarsTemplateEngine());


        //post: process a form to update a hero
        post("/heroes/:id/addBranch", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "heroSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual hero
        get("/heroes/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfHeroToDelete = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Hero deleteHero = Hero.findById(idOfHeroToDelete); //use it to find post
            deleteHero.deleteHero();
            return new ModelAndView(model, "heroSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all heroes
        get("/heroes/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Hero.clearAllHeroes();
            return new ModelAndView(model, "heroSuccess.hbs");
        }, new HandlebarsTemplateEngine());



        //Url for squad list
        get("/squads", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ArrayList<Squad> squads = Squad.getAll();
            model.put("squads", squads);
            return new ModelAndView(model, "squadList.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show new squad form
        get("/squads/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "squadForm.hbs");
        }, new HandlebarsTemplateEngine());

        post("/squads/new", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();

            String name = req.queryParams("name");
            int size = Integer.parseInt(req.queryParams("size"));
            String cause = req.queryParams("cause");

            Squad newSquad = new Squad(name,size,cause);
            model.put("squad", newSquad);
            return new ModelAndView(model, "squadSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual squad
        get("/squads/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToFind = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Squad foundSquad = Squad.findById(idOfSquadToFind); //use it to find post
            model.put("squad", foundSquad); //add it to model for template to display
            return new ModelAndView(model, "squadDetail.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());


        //get: show a form to update a squad
        get("/squads/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            model.put("editSquad", editSquad);
            return new ModelAndView(model, "squadForm.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a squad
        post("/squads/:id/update", (req, res) -> { //URL to make new post on POST route
            Map<String, Object> model = new HashMap<>();
            String newName = req.queryParams("name");
            int idOfSquadToEdit = Integer.parseInt(req.params("id"));
            Squad editSquad = Squad.findById(idOfSquadToEdit);
            editSquad.updateName(newName);
            return new ModelAndView(model, "squadSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete an individual squad
        get("/squads/:id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfSquadToDelete = Integer.parseInt(req.params(":id")); //pull id - must match route segment
            Squad deleteSquad = Squad.findById(idOfSquadToDelete); //use it to find post
            deleteSquad.deleteSquad();
            return new ModelAndView(model, "squadSuccess.hbs");
        }, new HandlebarsTemplateEngine());

        //get: delete all squads
        get("/squads/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Squad.clearAllSquads();
            return new ModelAndView(model, "squadSuccess.hbs");
        }, new HandlebarsTemplateEngine());




    }
}
