package lv.id.bonne.borderextender.configs;



import net.minecraftforge.common.ForgeConfigSpec;


/**
 * The configuration handling class. Holds all the config values.
 */
public class Configuration
{
    /**
     * The constructor for the config.
     */
    public Configuration()
    {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        this.borderMaxSize = builder.
            comment("The maximal world border size.").
            define("border_size", 20000);

        this.completeIncrement = builder.
            comment("The increment of border per completed vault.").
            define("complete_increment", 5);

        this.surviveIncrement = builder.
            comment("The increment of border per survived vault.").
            define("survive_increment", 1);

        this.deathIncrement = builder.
            comment("The increment of border per death in vault.").
            define("death_increment", 0);

        Configuration.GENERAL_SPEC = builder.build();
    }


// ---------------------------------------------------------------------
// Section: Getters
// ---------------------------------------------------------------------


    public int getBorderMaxSize()
    {
        return this.borderMaxSize.get();
    }


    public int getCompleteIncrement()
    {
        return this.completeIncrement.get();
    }


    public int getSurviveIncrement()
    {
        return this.surviveIncrement.get();
    }


    public int getDeathIncrement()
    {
        return this.deathIncrement.get();
    }


// ---------------------------------------------------------------------
// Section: Variables
// ---------------------------------------------------------------------

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> borderMaxSize;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> completeIncrement;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> surviveIncrement;

    /**
     * The config value for listing positive modifiers
     */
    private final ForgeConfigSpec.ConfigValue<Integer> deathIncrement;

    /**
     * The general config spec.
     */
    public static ForgeConfigSpec GENERAL_SPEC;
}
