package talent.bearers.slimefusion.common.block

import com.teamwizardry.librarianlib.common.base.block.BlockMod
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.math.AxisAlignedBB
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly
import talent.bearers.slimefusion.common.core.EnumPolestone
import talent.bearers.slimefusion.common.lib.LibNames

/**
 * @author WireSegal
 * Created at 10:54 PM on 12/23/16.
 */
class BlockMark : BlockMod(LibNames.MARK, Material.GLASS, *EnumPolestone.getNamesFor(LibNames.MARK)) {
    companion object {
        val POLESTONE: PropertyEnum<EnumPolestone> = PropertyEnum.create("polestone", EnumPolestone::class.java)

        val AABB = AxisAlignedBB(6 / 16.0, 6 / 16.0, 6 / 16.0, 10 / 16.0, 10 / 16.0, 10 / 16.0)
    }

    override fun getBoundingBox(state: IBlockState?, source: IBlockAccess?, pos: BlockPos?) = AABB
    override fun isFullCube(state: IBlockState) = false
    override fun isOpaqueCube(state: IBlockState) = false
    override fun damageDropped(state: IBlockState) = getMetaFromState(state)
    override fun getMetaFromState(state: IBlockState) = state.getValue(POLESTONE).ordinal
    override fun getStateFromMeta(meta: Int) = defaultState.withProperty(POLESTONE, EnumPolestone[meta])
    override fun createBlockState() = BlockStateContainer(this, POLESTONE)

    @SideOnly(Side.CLIENT)
    override fun getBlockLayer() = BlockRenderLayer.TRANSLUCENT
}
