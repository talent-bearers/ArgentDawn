package talent.bearers.slimefusion.common.core

import talent.bearers.slimefusion.common.block.base.EnumStringSerializable

/**
 * @author WireSegal
 * Created at 4:44 PM on 12/29/16.
 */
enum class EnumMetalType : EnumStringSerializable {
    COPPER, IRON, GOLD, TIN, CADMIUM, ALUMINUM, CHROMIUM, ZINC, LEAD, BISMUTH, NICKEL;

    companion object {
        operator fun get(meta: Int) = EnumMetalType.values().getOrElse(meta) { COPPER }

        fun getNamesFor(prefix: String) = EnumMetalType.values()
                .map { prefix + "_" + it.getName() }
                .toTypedArray()
    }
}

enum class EnumOreType(val mainDrop: EnumMetalType, val rareDrop: EnumMetalType? = null) : EnumStringSerializable {
    COPPER(EnumMetalType.COPPER, EnumMetalType.GOLD),
    IRON(EnumMetalType.IRON, EnumMetalType.NICKEL),
    TIN(EnumMetalType.TIN, EnumMetalType.ZINC),
    ALUMINUM(EnumMetalType.ALUMINUM),
    CHROMIUM(EnumMetalType.CHROMIUM, EnumMetalType.CADMIUM),
    LEAD(EnumMetalType.LEAD, EnumMetalType.BISMUTH);

    companion object {
        operator fun get(meta: Int) = EnumOreType.values().getOrElse(meta) { COPPER }

        fun getNamesFor(prefix: String) = EnumOreType.values()
                .map { prefix + "_" + it.getName() }
                .toTypedArray()
    }
}
