package epochflow.plugin.rsim.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import epochflow.plugin.rsim.Main;

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
	
	/**
	 * 파일의 버전을 업데이트 합니다.
	 * 없는 값만 새로 추가하여 처리합니다.
	 */
	public void updateVersion(String resPath)
	{
		String version = getString("version");
		
		if (version == null || version != Main.getInstance().getDescription().getVersion())
		{
			InputStream latestRes = Main.getInstance().getResource(resPath);
			YamlConfiguration latestConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(latestRes));
			Map<String, Object> values = latestConfig.getValues(false);
			values.forEach((key, value) ->
			{
				if (!this.contains(key))
					this.set(key, value);
			});
			this.set("version", Main.getInstance().getDescription().getVersion());
			this.save();
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