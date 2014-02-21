package org.rev666.inject;

import java.util.HashMap;

import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;
import org.parabot.core.Context;
import org.parabot.core.asm.ASMUtils;

/**
 * 
 * @author Everel
 *
 */
public class Messages implements Opcodes {
	
	public static void injectListener() {
		InsnList inject = new InsnList();
		Label l0 = new Label();
		inject.add(new LabelNode(l0));
		inject.add(new VarInsnNode(ILOAD, 0));
		inject.add(new VarInsnNode(ALOAD, 1));
		inject.add(new VarInsnNode(ILOAD, 2));
		inject.add(new VarInsnNode(ALOAD, 3));
		inject.add(new VarInsnNode(ALOAD, 4));
		inject.add(new VarInsnNode(ILOAD, 5));
		inject.add(new VarInsnNode(ALOAD, 6));
		inject.add(new VarInsnNode(ILOAD, 7));
		inject.add(new VarInsnNode(ALOAD, 8));
		inject.add(new MethodInsnNode(INVOKESTATIC, "org/rev666/callbacks/MessageListen", "messageReceived", "(ILjava/lang/String;ILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;ILjava/lang/String;)V"));
		
		HashMap<String, String> constants = Context.resolve().getHookParser().getConstants();
		
		ClassNode classNode = ASMUtils.getClass(constants.get("MessageHookClass"));
		for(final MethodNode methodNode : classNode.methods) {
			if(methodNode.name.equals(constants.get("MessageHookMethodName")) && methodNode.desc.equals(constants.get("MessageHookMethodDesc"))) {
				methodNode.instructions.insert(inject);
			}
		}
	}

}
