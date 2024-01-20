
# Main File Setup Example

---

Here is how one might setup their main file for their plugin so that they can hook into 
the Netuno API instance. <br>

_**Note:** Imports will always be presents in these examples to prevent wrong file imports
and other confusion._

```java
// Imports
import org.bukkit.plugin.java.JavaPlugin;
import com.github.cyberryan1.netunoapi.NetunoApi;

public class MainPlugin extends JavaPlugin {
    
    public static NetunoApi netunoApi = null;
    
    @Override
    public void onEnable() {
        
        // Grab the NetunoApi instance provided by Netuno 
        //      and save it into the netunoApi variable.
        netunoApi = this.getServer().getServicesManager().getRegistration( NetunoApi.class ).getProvider();
        // Now the Netuno API is accessible to any class 
        //      via the MainPlugin.netunoApi reference.
    }
}
```

---