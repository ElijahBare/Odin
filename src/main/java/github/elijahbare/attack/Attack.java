package github.elijahbare.attack;

import github.elijahbare.setting.Setting;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Elijah Bare 3/10/23
 */

public interface Attack {

    String run(String[] args);

    default Set<Setting> getSettings() {
        Set<Setting> ret = new HashSet<>();

        Set<Field> fields = Arrays.stream(this.getClass().getDeclaredFields())
                .filter(f -> f.getType() == Setting.class)
                .collect(Collectors.toSet());

        fields.forEach(f -> {
            try {
                ret.add((Setting) f.get(this));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        return ret;
    }

}
