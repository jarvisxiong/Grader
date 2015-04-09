package grader.permissions;

import java.io.File;

import framework.grading.testing.TestCase;
import framework.project.Project;

public interface PermissionsGenerator {
	File generatePermissions(Project aProject, TestCase aTestCase);

}
