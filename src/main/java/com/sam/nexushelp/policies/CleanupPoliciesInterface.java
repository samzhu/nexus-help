package com.sam.nexushelp.policies;

import com.sam.nexushelp.config.CleanupPolicy;
import com.sam.nexushelp.dto.search.ComponentItem;

import java.util.List;

public interface CleanupPoliciesInterface {

    List<ComponentItem> filterComponentItem(CleanupPolicy cleanupPolicy, List<ComponentItem> componentItems);
}
