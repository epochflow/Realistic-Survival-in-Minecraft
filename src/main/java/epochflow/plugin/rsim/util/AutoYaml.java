package epochflow.plugin.rsim.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

public class AutoYaml extends YamlConfiguration
{
	private File file;
	
	/**
	 * 로드한 경로에 저장합니다.
	 * @throws IOException
	 */
	public void save()
	{
		try {
			super.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static AutoYaml loadConfiguration(Reader reader, File file)
	{
		AutoYaml auto = new AutoYaml();
		try {
            auto.load(reader);
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration from stream", ex);
        } catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration from stream", ex);
        }
		auto.file = file;
		return auto;
	}
	
	public static AutoYaml loadConfiguration(File file)
	{
		AutoYaml auto = new AutoYaml();
		try {
            auto.load(file);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        } catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        }
		auto.file = file;
		return auto;
	}
	
	public void add(String path, int value)
	{
		int current = getInt(path);
		set(path, value + current);
	}
	
	public void add(String path, double value)
	{
		double current = getDouble(path);
		set(path, value + current);
	}
}