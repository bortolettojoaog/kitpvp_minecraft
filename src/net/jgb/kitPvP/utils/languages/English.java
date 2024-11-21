package net.jgb.kitPvP.utils.languages;

public class English extends Language {
	
	@Override
	protected void setLanguageStrings() {
		this.NO_PERMISSION = this.getMessageUtils().getErrorPrefix() + 
				" §cYou don't have permission!";
		
		this.CANNOT_DROP = this.getMessageUtils().getErrorPrefix() +
				" §cYou cannot drop this item!";
		
		this.ONLY_PLAYERS_CAN_RUN_COMMAND = this.getMessageUtils().getErrorPrefix() +
				" §cOnly players can execute §r{command} §ccommand!";
		
		this.TELEPORTED_TO_WARP = this.getMessageUtils().getSucessPrefix() +
				" §aTeleported to §r{warp}§a warp!";
		
		this.WARP_DOESNT_EXIST = this.getMessageUtils().getErrorPrefix() +
				" §cThe warp '§r{warp}§c' don't exist!";
		
		this.WARP_DELETED = this.getMessageUtils().getSucessPrefix() +
				" §aWarp §r{warp} §awas deleted!";
		
		this.WARP_CREATED = this.getMessageUtils().getSucessPrefix() +
				" §aWarp §r{warp} §awas created!";
		
		this.WARP_ALREADY_EXIST = this.getMessageUtils().getErrorPrefix() +
				" §cWarp §r{warp} §calready exist!";
		
		this.NO_WARPS = "§cNo warps created";
		
	    this.BOX_ITEM = "Mystery Box";
	    
	    this.KIT_ITEM = "Kit Menu";
		
	    this.TRAIN_ITEM = "Training Arena";
	    
	    this.NEXT_PAGE_INVENTORY = "Next Page";
	    
	    this.PREVIOUS_PAGE_INVENTORY = "Previous Page";
	    
	    this.BOX_MENU_TITLE = "Mystery Box";
	    
	    this.KIT_MENU_TITLE = "Selection Kit Menu";
	    
	    this.CURRENT_NAME = "Current Name: ";
	    
	    this.CURRENT_PAGE = "Current Page: ";
	    
	    this.CURRENT_STATE = "Current State: ";
	    
	    this.TELEPORTED_TO_RANDOM_PLAYER = "§aTeleported to a random player named §r{player}";
	}
}
