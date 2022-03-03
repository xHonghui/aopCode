package com.xhh.plugin;


import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @createTime: 2022/1/26
 * @author: xuhonghui
 * @description: new file
 */
public class LogMethodVisitor extends MethodVisitor implements Opcodes {

    public LogMethodVisitor(MethodVisitor methodVisitor) {
        super(Opcodes.ASM5, methodVisitor);
    }

    /**
     * 方法执行前插入
     */
    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitLdcInsn("tag");
        mv.visitLdcInsn("onCreate start");
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(POP);
    }

    /**
     * 方法执行后插入
     */
    @Override
    public void visitInsn(int opcode) {
        super.visitInsn(opcode);
        mv.visitLdcInsn("tag");
        mv.visitLdcInsn("onCreate end");
        mv.visitMethodInsn(INVOKESTATIC, "android/util/Log", "d", "(Ljava/lang/String;Ljava/lang/String;)I", false);
        mv.visitInsn(POP);
    }

    /**
     * 如果想在方法最后植入代码，在这里处理是不生效的
     */
    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
