package github.elijahbare.attack;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Elijah Bare 3/10/23
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface DefineAttack {

    String name();

    AttackType attackType();

}
