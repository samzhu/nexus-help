package com.sam.nexushelp.policies;

import com.sam.nexushelp.config.CleanupPolicy;
import com.sam.nexushelp.dto.search.ComponentItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PoliciesRegularExpressionExclude implements CleanupPoliciesInterface {
    @Override
    public List<ComponentItem> filterComponentItem(CleanupPolicy cleanupPolicy, List<ComponentItem> componentItems) {
        List<ComponentItem> componentItemList = null;
        if ("name" .equals(cleanupPolicy.getField())) {
            componentItemList = componentItems.stream().filter(item -> !item.getName().matches(cleanupPolicy.getRegex())).collect(Collectors.toList());
        } else if ("version" .equals(cleanupPolicy.getField())) {
            componentItemList = componentItems.stream().filter(item -> !item.getVersion().matches(cleanupPolicy.getRegex())).collect(Collectors.toList());
        }else{
            componentItemList = componentItems;
        }
        return componentItemList;
    }
}
