package github.elijahbare;

import github.elijahbare.manager.AttackManager;

/**
 * @author Elijah Bare 3/10/23
 */

public class Main {

    public static AttackManager attackManager;

    /**
     * Initialisation for managers and etc
     *
     * @throws InstantiationException on failed initialisation of module class
     * @throws IllegalAccessException on failed initialisation of module class
     */

    public static void init() throws InstantiationException, IllegalAccessException {
        attackManager = new AttackManager();

        UI.init();
    }

    /**
     * Entry Point
     *
     * @param args command line args
     */

    public static void main(String[] args) {

        try {
            init();
        } catch (InstantiationException | IllegalAccessException exception) {
            System.out.println("Failed to initialize with error: \n" + exception.getLocalizedMessage());
            System.exit(1);
        }
    }
}
