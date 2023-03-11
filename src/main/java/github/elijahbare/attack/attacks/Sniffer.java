package github.elijahbare.attack.attacks;

import github.elijahbare.attack.Attack;
import github.elijahbare.attack.AttackType;
import github.elijahbare.attack.DefineAttack;

/**
 * @author Elijah Bare
 * @since 2023-03-11
 */
@DefineAttack(name = "Sniffer", attackType = AttackType.LOCAL_NETWORK)
public class Sniffer implements Attack {

    @Override
    public String run(String[] args) {
        return "Not yet implemented";
    }
}