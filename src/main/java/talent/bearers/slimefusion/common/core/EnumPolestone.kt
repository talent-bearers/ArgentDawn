package talent.bearers.slimefusion.common.core

import talent.bearers.slimefusion.common.block.base.EnumStringSerializable

/**
 * @author WireSegal
 * Created at 10:24 PM on 12/23/16.
 */
enum class EnumPolestone : EnumStringSerializable {
    SAPPHIRE, SMOKESTONE, RUBY, DIAMOND, EMERALD, GARNET, ZIRCON, AMETHYST, TOPAZ, HELIODOR;

    companion object {
        operator fun get(meta: Int)= EnumPolestone.values().getOrElse(meta) { SAPPHIRE }

        fun getNamesFor(prefix: String) = EnumPolestone.values()
                .map { prefix + "_" + it.getName() }
                .toTypedArray()
    }
}
