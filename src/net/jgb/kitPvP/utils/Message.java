package net.jgb.kitPvP.utils;

import net.jgb.kitPvP.enums.LanguagesEnum;
import net.jgb.kitPvP.utils.languages.English;
import net.jgb.kitPvP.utils.languages.Language;
import net.jgb.kitPvP.utils.languages.Portuguese;

public class Message {

    public Message() {

    }

    private String information = "§8§l(§6§l!§8§l)§r";
    private String success = "§8§l(§2§l!§8§l)§r";
    private String error = "§8§l(§4§l!§8§l)§r";
    private String message = "§8§l(§e§l•§8§l)§r";

    public String getInformationPrefix() {
        return information;
    }

    public String getSucessPrefix() {
        return success;
    }

    public String getErrorPrefix() {
        return error;
    }

    public String getMessagePrefix() {
        return message;
    }
    
    public Language getLanguage(LanguagesEnum language) {
    	switch (language) {
        case PORTUGUESE:
            return new Portuguese();
        case ENGLISH:
            return new English();
        default:
            throw new IllegalArgumentException("This language doesn't exist in the config!");
    	}
    }
}
