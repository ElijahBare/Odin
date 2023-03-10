package github.elijahbare.manager;

import github.elijahbare.attack.Attack;
import github.elijahbare.attack.AttackType;
import github.elijahbare.attack.DefineAttack;
import org.reflections.Reflections;

import java.util.*;

/**
 * @author Elijah Bare 3/10/23
 */

public class AttackManager {
    public static Map<Attack, AttackType> attacks = new HashMap<>();

    /**
     * Handles attacks
     */

    public AttackManager() throws InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("github.elijahbare.attack.attacks");

        Set<Class<? extends Attack>> allClasses =
                new HashSet<>(reflections.getSubTypesOf(Attack.class));

        for (Class<? extends Attack> moduleClass : allClasses) {
            Attack module = moduleClass.newInstance();
            attacks.put(module, moduleClass.getAnnotation(DefineAttack.class).attackType());
            System.out.println(moduleClass.getAnnotation(DefineAttack.class).name());
        }
    }

    public static Map<Attack, AttackType> getAttacks() {
        return attacks;
    }

    public ArrayList<Attack> getAttacksByCategory(AttackType type) {
        ArrayList<Attack> ret = new ArrayList<>();

        for (Attack attack : attacks.keySet()) {
            if (attack.getClass().getAnnotation(DefineAttack.class).attackType() == type) {
                ret.add(attack);
            }
        }
        return ret;
    }

    public String getAttackName(Attack attack) {
        String name = attack.getClass().getAnnotation(DefineAttack.class).name();
        if (name != null) {
            return name;
        }
        return "Could Not Fetch Name";
    }
}

