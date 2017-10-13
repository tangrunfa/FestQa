package com.example.compiler;

import com.example.annotations.AppRegisterGenerator;
import com.example.annotations.EntryGenerator;
import com.example.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by Administrator on 2017/10/12.
 */
@SuppressWarnings("unused")
@AutoService(Process.class)
public class LatteProcessor extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        generateAppRegisterCode(roundEnv);
        generateEntryCode(roundEnv);
        generatePayEntryCode(roundEnv);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotaion : supportAnnotations) {
            types.add(annotaion.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotion = new LinkedHashSet<>();
        annotion.add(EntryGenerator.class);
        annotion.add(PayEntryGenerator.class);
        annotion.add(AppRegisterGenerator.class);
        return annotion;
    }

    private void scan(RoundEnvironment ev, Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement : ev.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMIrrors = typeElement.getAnnotationMirrors();
            for (AnnotationMirror annotationMirror : annotationMIrrors
                    ) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = annotationMirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry
                        : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }

    }

    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    private void generatePayEntryCode(RoundEnvironment env) {
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor();
        payEntryVisitor.setFiler(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, payEntryVisitor);

    }

    private void generateAppRegisterCode(RoundEnvironment env) {
        final AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor();
        appRegisterVisitor.setFiler(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, appRegisterVisitor);

    }
}
