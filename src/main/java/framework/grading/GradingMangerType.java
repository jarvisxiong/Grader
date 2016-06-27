package framework.grading;

/**
 *
 * @author Andrew Vitkus
 */
public enum GradingMangerType {
    A_GUI_GRADING_MANAGER, A_HEADLESS_GRADING_MANAGER, SAKAI_PROJECT_DATABASE, NONE;
    public static final String HEADLESS_GRADING_MANAGER = "AHeadlessGradingManager";    
    public static GradingMangerType getFromConfigName(String name) {
        switch (name) {
            case "AGUIGradingManager":
                return A_GUI_GRADING_MANAGER;
//            case "AHeadlessGradingManager":
            case HEADLESS_GRADING_MANAGER:

                return A_HEADLESS_GRADING_MANAGER;
            case "SakaiProjectDatabase":
                return SAKAI_PROJECT_DATABASE;
            default:
                return NONE;
        }
    }
}
