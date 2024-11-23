package net.jgb.kitPvP.utils.languages;

import net.jgb.kitPvP.Main;
import net.jgb.kitPvP.utils.Message;

public abstract class Language {
    
    private Message messageUtils;
    
    public String NO_PERMISSION;
    public String CANNOT_DROP;
    public String ONLY_PLAYERS_CAN_RUN_COMMAND;
    public String TELEPORTED_TO_WARP;
    public String WARP_DOESNT_EXIST;
    public String WARP_DELETED;
    public String WARP_CREATED;
    public String WARP_ALREADY_EXIST;
    public String NO_WARPS;
    public String BOX_ITEM;
    public String KIT_ITEM;
    public String TRAIN_ITEM;
    public String NEXT_PAGE_INVENTORY;
    public String PREVIOUS_PAGE_INVENTORY;
    public String KIT_MENU_TITLE;
    public String BOX_MENU_TITLE;
    public String CURRENT_PAGE;
    public String CURRENT_NAME;
    public String CURRENT_STATE;
    public String TELEPORTED_TO_RANDOM_PLAYER;

    public Language() {
        this.setMessageUtils(Main.getUtils().messageUtils());
        this.setLanguageStrings();
    }

    protected abstract void setLanguageStrings();

    public Message getMessageUtils() {
        return messageUtils;
    }

    public void setMessageUtils(Message messageUtils) {
        this.messageUtils = messageUtils;
    }
}
