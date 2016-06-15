package framework.project;

import java.io.IOException;

import com.github.antlrjavaparser.api.CompilationUnit;

public interface ParsableClassDescription extends ClassDescription {
	/**
     * @return The parsed code
     */
    public CompilationUnit parse() throws IOException;

}
