package com.cannolicatfish.rankine.client.renders;

import com.cannolicatfish.rankine.client.models.BronzeSpearEntityModel;
import com.cannolicatfish.rankine.client.models.FlintSpearEntityModel;
import com.cannolicatfish.rankine.client.models.IronSpearEntityModel;
import com.cannolicatfish.rankine.client.models.SteelSpearEntityModel;
import com.cannolicatfish.rankine.entities.SpearEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SpearEntityRenderer extends EntityRenderer<SpearEntity> {
    public static final ResourceLocation FLINT_SPEAR = new ResourceLocation("rankine:textures/entity/flint_spear.png");
    public static final ResourceLocation BRONZE_SPEAR = new ResourceLocation("rankine:textures/entity/bronze_spear.png");
    public static final ResourceLocation IRON_SPEAR = new ResourceLocation("rankine:textures/entity/iron_spear.png");
    public static final ResourceLocation STEEL_SPEAR = new ResourceLocation("rankine:textures/entity/steel_spear.png");

    private final FlintSpearEntityModel flintspearModel = new FlintSpearEntityModel();
    private final BronzeSpearEntityModel bronzespearModel = new BronzeSpearEntityModel();
    private final IronSpearEntityModel ironspearModel = new IronSpearEntityModel();
    private final SteelSpearEntityModel steelspearModel = new SteelSpearEntityModel();


    public SpearEntityRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    @Override
    public void render(SpearEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        matrixStackIn.push();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) - 90.0F));
        matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationPitch, entityIn.rotationPitch) + 90.0F));

        if (entityIn.type == 0)
        {
            IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getBuffer(bufferIn, this.flintspearModel.getRenderType(this.getEntityTexture(entityIn)), false, false);
            this.flintspearModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        else if (entityIn.type == 1)
        {
            IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getBuffer(bufferIn, this.bronzespearModel.getRenderType(this.getEntityTexture(entityIn)), false, false);
            this.bronzespearModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        else if (entityIn.type == 2)
        {
            IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getBuffer(bufferIn, this.ironspearModel.getRenderType(this.getEntityTexture(entityIn)), false, false);
            this.ironspearModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        else if (entityIn.type == 3)
        {
            IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getBuffer(bufferIn, this.steelspearModel.getRenderType(this.getEntityTexture(entityIn)), false, false);
            this.steelspearModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        } else
        {
            IVertexBuilder ivertexbuilder = net.minecraft.client.renderer.ItemRenderer.getBuffer(bufferIn, this.steelspearModel.getRenderType(this.getEntityTexture(entityIn)), false, false);
            this.steelspearModel.render(matrixStackIn, ivertexbuilder, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getEntityTexture(SpearEntity entity) {
        if (entity.type == 0)
        {
            return FLINT_SPEAR;
        }
        else if (entity.type == 1)
        {
            return BRONZE_SPEAR;
        }
        else if (entity.type == 2)
        {
            return IRON_SPEAR;
        }
        else if (entity.type == 3)
        {
            return STEEL_SPEAR;
        }
        else
        {
            return STEEL_SPEAR;
        }

    }
}