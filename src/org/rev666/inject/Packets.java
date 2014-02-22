package org.rev666.inject;

import java.util.HashMap;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.parabot.core.Context;
import org.parabot.core.asm.ASMUtils;

/**
 * 
 * @author Everel
 * 
 *         Injects methods used for invoking doAction
 */
public class Packets implements Opcodes {

	public static void injectIdGetter() {
		HashMap<String, String> constants = Context.getInstance().getHookParser().getConstants();
		
		ClassNode client = ASMUtils.getClass(constants.get("ClientClass"));
		MethodNode mv = new MethodNode(ACC_PUBLIC, "getActionKey",
				"(Lorg/rev666/accessors/Animable;II)J", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitIntInsn(BIPUSH, 97);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, constants.get("MenuClass"));
		mv.visitVarInsn(ILOAD, 3);
		mv.visitVarInsn(ILOAD, 2);
		mv.visitMethodInsn(INVOKESTATIC, constants.get("MenuFetcherClass"), constants.get("GetMenuMethodName"),
				constants.get("GetMenuMethodDesc"));
		mv.visitInsn(LRETURN);
		Label l1 = new Label();
		mv.visitLabel(l1);
		// TODO : remove useless instructions....
		mv.visitLocalVariable("this", "Lclient;", null, l0, l1, 0);
		mv.visitLocalVariable("animable", "Lorg/rev666/accessors/Animable;",
				null, l0, l1, 1);
		mv.visitLocalVariable("localX", "I", null, l0, l1, 2);
		mv.visitLocalVariable("localY", "I", null, l0, l1, 3);
		mv.visitMaxs(4, 4);
		mv.visitEnd();
		client.methods.add(mv);
	}

	public static void injectInvoker() {
		//HashMap<String, String> constants = Context.resolve().getHookParser().getConstants();
		// TODO: use hook file instead of hardcoded constants
		
		
		ClassNode client = ASMUtils.getClass("client");
		MethodNode mv = new MethodNode(ACC_PUBLIC, "doAction",
				"(Lorg/rev666/accessors/Menu;)V", null, null);
		mv.visitCode();
		Label l0 = new Label();
		mv.visitLabel(l0);
		mv.visitIntInsn(BIPUSH, 100);
		mv.visitIntInsn(BIPUSH, 100);
		mv.visitIntInsn(BIPUSH, 90);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, "Class296_Sub39_Sub9");
		mv.visitMethodInsn(INVOKESTATIC, "Class97", "method873",
				"(IIILClass296_Sub39_Sub9;)V");
		Label l1 = new Label();
		mv.visitLabel(l1);
		mv.visitInsn(RETURN);
		Label l2 = new Label();
		mv.visitLabel(l2);
		mv.visitLocalVariable("this", "Lclient;", null, l0, l2, 0);
		mv.visitLocalVariable("menu", "Lorg/rev666/accessors/Menu;", null,
				l0, l2, 1);
		mv.visitMaxs(4, 2);
		mv.visitEnd();
		client.methods.add(mv);
	}
	
	public static void injectDebug() {
		//HashMap<String, String> constants = Context.resolve().getHookParser().getConstants();
		// TODO: use hook file instead of hardcoded constants
		
		InsnList list = new InsnList();
		list.add(new VarInsnNode(ALOAD, 3));
		list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "org/rev666/callbacks/PacketListen", "debug", "(Lorg/rev666/accessors/Menu;)V"));
		ClassNode classNode = ASMUtils.getClass("Class97");
		for(final MethodNode methodNode : classNode.methods) {
			if(methodNode.name.equals("method873")) {
				methodNode.instructions.insert(list);
			}
		}
	}

}
