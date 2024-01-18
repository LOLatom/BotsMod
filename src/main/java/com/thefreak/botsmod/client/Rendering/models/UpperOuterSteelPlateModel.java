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

public class UpperOuterSteelPlateModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "upperoutersteelplate"), "main");
	private final ModelPart UpperOuterSteelPlate;

	public UpperOuterSteelPlateModel(ModelPart root) {
		this.UpperOuterSteelPlate = root.getChild("UpperOuterSteelPlate");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition UpperOuterSteelPlate = partdefinition.addOrReplaceChild("UpperOuterSteelPlate", CubeListBuilder.create().texOffs(0, 11).addBox(-3.75F, -2.5F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(-0.25F))
		.texOffs(0, 0).addBox(-3.75F, -2.5F, -2.5F, 5.0F, 6.0F, 5.0F, new CubeDeformation(-0.26F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	public void copyFromArm(ModelPart arm) {
		this.UpperOuterSteelPlate.x = arm.x;
		this.UpperOuterSteelPlate.y = arm.y;
		this.UpperOuterSteelPlate.z = arm.z;
		this.UpperOuterSteelPlate.xRot = arm.xRot;
		this.UpperOuterSteelPlate.yRot = arm.yRot;
		this.UpperOuterSteelPlate.zRot = arm.zRot;

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		UpperOuterSteelPlate.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}