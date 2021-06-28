package com.samsungds.atlassian.jira.plugin.ao;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import net.java.ao.Query;

import javax.inject.Inject;
import javax.inject.Named;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;

// @Scanned
@Named
public class ShortcutServiceImpl implements ShortcutService {
    @ComponentImport
    private final ActiveObjects ao;

    @Inject
    public ShortcutServiceImpl(ActiveObjects ao) {
        this.ao = checkNotNull(ao);
    }

    @Override
    public Shortcut add(ShortcutVo shortcutVo) {
        final Shortcut shortcut = ao.create(Shortcut.class);
        shortcut.setName(shortcutVo.getName());
        shortcut.setUrl(shortcutVo.getUrl());
        shortcut.setUserName(shortcutVo.getUserName());
        shortcut.save();
        return shortcut;
    }

    @Override
    public List<Shortcut> getAllByUserName(String userName) {
        return newArrayList(ao.find(Shortcut.class, Query.select().where("userName = ?", userName)));
    }

    @Override
    public Shortcut modify(ShortcutVo shortcutVo) {
        List<Shortcut> shortcutList = newArrayList(ao.find(Shortcut.class, Query.select().where("ID = ?", shortcutVo.getId())));
        Shortcut shortcut = shortcutList.get(0);
        shortcut.setName(shortcutVo.getName());
        shortcut.setUrl(shortcutVo.getUrl());
        shortcut.setUserName(shortcutVo.getUserName());
        shortcut.save();
        return shortcut;
    }

    @Override
    public Shortcut remove(Long id) {
        List<Shortcut> shortcutList = newArrayList(ao.find(Shortcut.class, Query.select().where("ID = ?", id)));
        Shortcut shortcut = shortcutList.get(0);
        ao.delete(shortcut);
        return shortcut;
    }
}
