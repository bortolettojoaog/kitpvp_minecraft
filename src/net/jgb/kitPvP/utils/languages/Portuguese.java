package net.jgb.kitPvP.utils.languages;

public class Portuguese extends Language {
	
	@Override
	protected void setLanguageStrings() {
		this.NO_PERMISSION = this.getMessageUtils().getErrorPrefix() + 
				" �cVoc� n�o tem permiss�o para isso!";
		
		this.CANNOT_DROP = this.getMessageUtils().getErrorPrefix() +
				" �cVoc� n�o pode dropar este item!";
		
		this.ONLY_PLAYERS_CAN_RUN_COMMAND = this.getMessageUtils().getErrorPrefix() +
				" �cApenas jogadores podem executar o comando �r{command}�c!";
		
		this.TELEPORTED_TO_WARP = this.getMessageUtils().getSucessPrefix() +
				" �aTeletransportado at� a warp �r{warp}�a!";
		
		this.WARP_DOESNT_EXIST = this.getMessageUtils().getErrorPrefix() +
				" �cA warp '�r{warp}�c' n�o existe!";
		
		this.WARP_DELETED = this.getMessageUtils().getSucessPrefix() +
				" �aA warp �r{warp} �afoi deletada!";
		
		this.WARP_CREATED = this.getMessageUtils().getSucessPrefix() +
				" �aA warp �r{warp} �afoi criada!";
		
		this.WARP_ALREADY_EXIST = this.getMessageUtils().getErrorPrefix() +
				" �cA warp �r{warp} �cj� existe!";
		
		this.NO_WARPS = "�cNenhuma warp criada!";
		
	    this.BOX_ITEM = "Caixa Surpresa";
	    
	    this.KIT_ITEM = "Menu Kits";
		
	    this.TRAIN_ITEM = "Area de Treino";
	    
	    this.NEXT_PAGE_INVENTORY = "Pr�xima P�gina";
	    
	    this.PREVIOUS_PAGE_INVENTORY = "P�gina Anterior";
	    
	    this.BOX_MENU_TITLE = "Caixa Surpresa";
	    
	    this.KIT_MENU_TITLE = "Menu de Kits";
	    
	    this.CURRENT_NAME = "Nome Atual: ";
	    
	    this.CURRENT_PAGE = "P�gina Atual: ";
	    
	    this.CURRENT_STATE = "Estado Atual: ";
	}
}
