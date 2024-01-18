package com.thefreak.botsmod.client.Rendering.models;// Made with Blockbench 4.9.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class LongBladeModuleModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "longblademodule"), "main");
	private final ModelPart LongBladeModule;

	public LongBladeModuleModel(ModelPart root) {
		this.LongBladeModule = root.getChild("LongBladeModule");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition LongBladeModule = partdefinition.addOrReplaceChild("LongBladeModule", CubeListBuilder.create().texOffs(0, 0).addBox(-6.25F, 3.0F, 0.0F, 3.0F, 14.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	public void copyFromArm(ModelPart arm) {
		this.LongBladeModule.x = arm.x;
		this.LongBladeModule.y = arm.y;
		this.LongBladeModule.z = arm.z;
		this.LongBladeModule.xRot = arm.xRot;
		this.LongBladeModule.yRot = arm.yRot;
		this.LongBladeModule.zRot = arm.zRot;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		LongBladeModule.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}