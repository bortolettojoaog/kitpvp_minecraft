package net.jgb.kitPvP.utils.languages;

public class Portuguese extends Language {
	
	@Override
	protected void setLanguageStrings() {
		this.NO_PERMISSION = this.getMessageUtils().getErrorPrefix() + 
				" §cVocê não tem permissão para isso!";
		
		this.CANNOT_DROP = this.getMessageUtils().getErrorPrefix() +
				" §cVocê não pode dropar este item!";
		
		this.ONLY_PLAYERS_CAN_RUN_COMMAND = this.getMessageUtils().getErrorPrefix() +
				" §cApenas jogadores podem executar o comando §r{command}§c!";
		
		this.TELEPORTED_TO_WARP = this.getMessageUtils().getSucessPrefix() +
				" §aTeletransportado até a warp §r{warp}§a!";
		
		this.WARP_DOESNT_EXIST = this.getMessageUtils().getErrorPrefix() +
				" §cA warp '§r{warp}§c' não existe!";
		
		this.WARP_DELETED = this.getMessageUtils().getSucessPrefix() +
				" §aA warp §r{warp} §afoi deletada!";
		
		this.WARP_CREATED = this.getMessageUtils().getSucessPrefix() +
				" §aA warp §r{warp} §afoi criada!";
		
		this.WARP_ALREADY_EXIST = this.getMessageUtils().getErrorPrefix() +
				" §cA warp §r{warp} §cjá existe!";
		
		this.NO_WARPS = "§cNenhuma warp criada!";
		
	    this.BOX_ITEM = "Caixa Surpresa";
	    
	    this.KIT_ITEM = "Menu Kits";
		
	    this.TRAIN_ITEM = "Area de Treino";
	    
	    this.NEXT_PAGE_INVENTORY = "Próxima Página";
	    
	    this.PREVIOUS_PAGE_INVENTORY = "Página Anterior";
	    
	    this.BOX_MENU_TITLE = "Caixa Surpresa";
	    
	    this.KIT_MENU_TITLE = "Menu de Kits";
	    
	    this.CURRENT_NAME = "Nome Atual: ";
	    
	    this.CURRENT_PAGE = "Página Atual: ";
	    
	    this.CURRENT_STATE = "Estado Atual: ";
	}
}
