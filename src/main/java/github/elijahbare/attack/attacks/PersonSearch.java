package github.elijahbare.attack.attacks;

import github.elijahbare.attack.Attack;
import github.elijahbare.attack.AttackType;
import github.elijahbare.attack.DefineAttack;
import github.elijahbare.setting.Setting;

import java.util.ArrayList;

/**
 * @author Elijah Bare 3/10/23
 */


@DefineAttack(name = "PersonSearch", attackType = AttackType.OSINT)
public class PersonSearch implements Attack {

    public final Setting<Integer> depth = new Setting<Integer>("Depth", 5,1,10);

    @Override
    public String run(String[] args) {
        return "Not implemented";
    }


}
