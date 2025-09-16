package com.company.jmixpmdata.security;

import com.company.jmixpmdata.entity.TimeEntry;
import com.company.jmixpmdata.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "Own time entries", code = OwnTimeEntriesRole.CODE)
public interface OwnTimeEntriesRole {
    String CODE = "own-time-entries-role";

    @PredicateRowLevelPolicy(entityClass = TimeEntry.class, actions = {RowLevelPolicyAction.UPDATE, RowLevelPolicyAction.DELETE})
    default RowLevelBiPredicate<TimeEntry, ApplicationContext> timeEntryPredicate() {
        return (timeEntry, applicationContext) -> {
            CurrentAuthentication authentication = applicationContext.getBean(CurrentAuthentication.class);
            User user = (User) authentication.getUser();

            return timeEntry.getUser().equals(user);
        };
    }

//    @JpqlRowLevelPolicy(entityClass = TimeEntry.class, where = "{E}.user.id = :current_user_id")
//    void timeEntry();


}