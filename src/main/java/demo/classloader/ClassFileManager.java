package demo.classloader;

import javax.tools.*;
import java.io.IOException;

public class ClassFileManager extends
		ForwardingJavaFileManager {
    public JavaClassObject getJavaClassObject() {
        return jclassObject;
    }

    private JavaClassObject jclassObject;


    public ClassFileManager(StandardJavaFileManager
        standardManager) {
        super(standardManager);
    }


    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
        String className, JavaFileObject.Kind kind, FileObject sibling)
            throws IOException {
            jclassObject = new JavaClassObject(className, kind);
        return jclassObject;
    }
}