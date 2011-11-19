package net.teamio;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ThreadHelper {
	

	private Logger log = Logger.getLogger("Minecraft");
	private String defdir = "plugins" + File.separator;
	private String name;
	/* configuration files */
	public FileConfiguration mysqlconfig;
	private File mysqlconfigfile = new File(defdir + "mysql.yml");
	public FileConfiguration defaults;
	private File defaultsfile = new File(defdir + "defaults.yml");
	
	public ThreadHelper(String name){
		this.name=name;
		defdir+= this.name + File.separator;
	}
	
	public String getDefdir(){
		return defdir;
	}
	
	public Logger getLog(){
		return log;
	}
	
	/**
	 * load/save defaults.yml configuration
	 * @param save false to load, true to save
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	public void actDefaultsFile(boolean save) throws IOException, InvalidConfigurationException{
		if (!save){
			defaultsfile.mkdir();
			defaults.load(defaultsfile);
		}
		else
			defaults.save(defaultsfile);
	}
	
	/**
	 * load/save mysql.yml configuration
	 * @param save false to load, true to save
	 * @throws IOException
	 * @throws InvalidConfigurationException
	 */
	public void actMySQLFile(boolean save) throws IOException, InvalidConfigurationException{
		if (!save){
			mysqlconfigfile.mkdir();
			mysqlconfig.load(mysqlconfigfile);
		}
		else
			mysqlconfig.save(mysqlconfigfile);
	}

	// 0 = info
	// 1 = warning
	// -1 = severe
	public void print(String message, int level){
		print(null,message,level);
	}
	
	/* implied info */
	public void print(Player player, String message){
		print(player,message,0);
	}
	
	// ChatColor.<COLOUR> is a string.
	public void print(Player player, String message, int level){
		if (!player.equals(null)){
			player.sendMessage(message);
		}
		else{
			if (level==1)
				log.warning("["+name+" ~] " + message);
			else if (level==-1)
				log.severe("["+name+" !] " + message);
			else
				log.info("["+name+"] " + message);
		}
	}
}
