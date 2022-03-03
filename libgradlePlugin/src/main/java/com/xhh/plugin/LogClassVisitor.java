package com.xhh.plugin;


import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @createTime: 2022/1/26
 * @author: xuhonghui
 * @description:
 */
public class LogClassVisitor extends ClassVisitor implements Opcodes {

    public LogClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if ("android/support/v4/app/FragmentActivity".equals(this.getClass().getName())) {
            if ("onCreate".equals(name) ) {
                //处理onCreate
                return new LogMethodVisitor(methodVisitor);
            }
        }
        return methodVisitor;
    }
}
