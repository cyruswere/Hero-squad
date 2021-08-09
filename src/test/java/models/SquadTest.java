package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SquadTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void NewSquadObjectGetsCorrectlyCreated_true() throws  Exception{
        Squad squad = new Squad("Vipers",4,"Women power");
        assertEquals(true,squad instanceof Squad);
    }

    @Test
    public void SquadInstantiatesWithCorrectName_true() throws Exception{
        Squad squad = new Squad("Vipers",4,"Women power");
        assertEquals("Odero",squad.getName());
    }

    @Test
    public void SquadInstantiatesWithCorrectCause_true() throws Exception{
        Squad squad = new Squad("Vipers",4,"Women power");
        assertEquals("Women power",squad.getCause());
    }

    public Squad setUpNewSquad(){
        return new Squad("Vipers",4,"Women power");
    }

    @Test
    public void AddHeroesToSquads_Heros(){
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        Hero heroTwo = new Hero("Oluoch",15,"Fire","Tail");
        Squad squad = setUpNewSquad();
        squad.setHeroes(hero);
        squad.setHeroes(heroTwo);
        assertEquals(2,squad.getHeroes().size());

    }

    @Test
    public void AllSquadsContainsAllSquads_true(){
        Squad squad = new Squad("Vipers",4,"Women power");
        Squad squadTwo = new Squad("ViKings",5,"Dragons");
        assertTrue(Squad.getAll().contains(squadTwo));
        assertTrue(Squad.getAll().contains(squad));
    }

    @Test
    public void findReturnsCorrectSquad() throws Exception{
        Squad squad = setUpNewSquad();
        assertEquals(1,squad.findById(squad.getId()).getId());
    }

    @Test
    public void findReturnsCorrectSquadWhenMoreThanOneSquadsExists() throws Exception{
        Squad squad = setUpNewSquad();
        Squad squadTwo = new Squad("Vipers",4,"Women power");
        assertEquals(2,Squad.findById(squadTwo.getId()).getId());
    }

    @Test
    public void updateSquadName() throws Exception {
        Squad squad = setUpNewSquad();
        String formerName = squad.getName();
        squad.updateName("Otieno");
        assertNotEquals(formerName, squad.getName());
    }

    @Test
    public void deleteDeletesASpecificSquad() throws Exception {
        Squad squad = setUpNewSquad();
        Squad  squadTwo = new Squad("Vipers",4,"Women power");
        squad.deleteSquad();
        assertEquals(1, Squad.getAll().size()); //one is left
        assertEquals(Squad.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
    }

    @Test
    public void deleteAllSquadsDeletesAllSquads() throws Exception {
        Squad squad = setUpNewSquad();
        Squad squadTwo = setUpNewSquad();
        Squad.clearAllSquads();
        assertEquals(0, Squad.getAll().size());
    }

}