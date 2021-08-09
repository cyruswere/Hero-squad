package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        Hero.clearAllHeroes();
    }

    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws  Exception{
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        assertEquals(true,hero instanceof Hero);
    }

    @Test
    public void HeroInstantiatesWithCorrectName_true() throws Exception{
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        assertEquals("Odero",hero.getName());
    }

    @Test
    public void PostInstantiatesWithCorrectPowers_true() throws Exception{
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        assertEquals("Eyes",hero.getPowers());
    }

    @Test
    public void PostInstantiatesWithCorrectAge_true() throws Exception{
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        assertEquals(14,hero.getAge());
    }

    @Test
    public void PostInstantiatesWithCorrectId_true() throws Exception{
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        assertEquals(0,hero.getId());
    }

    @Test
    public void PostInstantiatesWithCorrectWeakness_true() throws Exception{
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        assertEquals("Light",hero.getWeakness());
    }

    @Test
    public void AllHeroInstancesAreCorrectlyReturned_true(){
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        Hero heroTwo = new Hero("Oluoch",15,"Fire","Tail");
        assertEquals(2,Hero.getAll().size());
    }

    @Test
    public void AllHeroesContainsAllHeroes_true(){
        Hero hero = new Hero("Odero",14,"Light","Eyes");
        Hero heroTwo = new Hero("Oluoch",15,"Fire","Tail");
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(heroTwo));
    }

    public Hero setUpNewHero(){
        return new Hero("Odero",14,"Light","Eyes");
    }

    @Test
    public void findReturnsCorrectHero() throws Exception{
        Hero hero = setUpNewHero();
        assertEquals(1,Hero.findById(hero.getId()).getId());
    }

    @Test
    public void findReturnsCorrectHeroWhenMoreThanOneHeroesExists() throws Exception{
        Hero hero = setUpNewHero();
        Hero anotherHero = new Hero("Odero",14,"Light","Eyes");
        assertEquals(2,Hero.findById(anotherHero.getId()).getId());
    }

    @Test
    public void updateHeroName() throws Exception {
        Hero hero = setUpNewHero();
        String formerName = hero.getName();
        hero.updateName("Otieno");
        assertNotEquals(formerName, hero.getName());
    }

    @Test
    public void deleteDeletesASpecificHero() throws Exception {
        Hero hero = setUpNewHero();
        Hero anotherHero = new Hero("Odero",14,"Light","Eyes");
        hero.deleteHero();
        assertEquals(1, Hero.getAll().size()); //one is left
        assertEquals(Hero.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
    }

    @Test
    public void deleteAllHeroesDeletesAllHeroes() throws Exception {
        Hero hero = setUpNewHero();
        Hero anotherHero = setUpNewHero();

        Hero.clearAllHeroes();
        assertEquals(0, Hero.getAll().size());
    }

}