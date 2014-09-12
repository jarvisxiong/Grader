package grader.sakai;

import grader.file.FileProxy;
import grader.file.FileProxyUtils;
import grader.trace.sakai_bulk_folder.CommentsFileLoaded;
import grader.trace.sakai_bulk_folder.DocumentFileLoaded;
import grader.trace.sakai_bulk_folder.FeedbackFolderLoaded;
import grader.trace.sakai_bulk_folder.SubmissionFolderLoaded;
import grader.trace.sakai_bulk_folder.SubmissionFolderNotFound;
import grader.trace.sakai_bulk_folder.TimestampFileLoaded;
import util.misc.Common;
import util.trace.Tracer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class ASakaiStudentAssignment implements StudentAssignment {

    public static String SUBMISSION_LOCAL_NAME = "Submission attachment(s)";
    public static String FEEDBACK_LOCAL_NAME = "Feedback Attachment(s)";
    public static String COMMENTS_LOCAL_NAME = "comments.txt";
    public static String TIMESTAMP_LOCAL_NAME = "timestamp.txt";

    FileProxy submissionFolder, feedbackFolder, commentsFile, timeStampFile;
    Date date;
    String timeStamp;
    String studentDescription;
    String name, onyen;
    boolean submitted;
    List<String> documents = new ArrayList();
    String commentsFileName;
    FileProxy studentFolder;

    public ASakaiStudentAssignment(String aStudentDescription, FileProxy aFileProxy) {
        try {
            studentDescription = aStudentDescription;
            int parenIndex = aStudentDescription.indexOf("(");
            name = aStudentDescription.substring(0, parenIndex);
            onyen = aStudentDescription.substring(parenIndex + 1, studentDescription.length() - 1);
            studentFolder = aFileProxy;
            submissionFolder = aFileProxy.getFileEntryFromLocalName(SUBMISSION_LOCAL_NAME);
            if (submissionFolder != null) {
                SubmissionFolderLoaded.newCase(submissionFolder.getAbsoluteName(), this);
            } else {
                throw SubmissionFolderNotFound.newCase(onyen, this);
            }
//            System.out.println("*^*^* " + aFileProxy.getMixedCaseAbsoluteName());
            feedbackFolder = aFileProxy.getFileEntryFromLocalName(FEEDBACK_LOCAL_NAME);
            FeedbackFolderLoaded.newCase(feedbackFolder.getAbsoluteName(), this);

            commentsFile = aFileProxy.getFileEntryFromLocalName(COMMENTS_LOCAL_NAME);
            if (commentsFile != null) {
                //commentsFileName = commentsFile.getAbsoluteName();
                commentsFileName = commentsFile.getMixedCaseAbsoluteName();
                CommentsFileLoaded.newCase(commentsFileName, this);

            }
            timeStampFile = aFileProxy.getFileEntryFromLocalName(TIMESTAMP_LOCAL_NAME);
            try {
                timeStamp = FileProxyUtils.toText(timeStampFile);
                if (timeStamp != null) {
                    date = Common.toDate(timeStamp);
                    TimestampFileLoaded.newCase(timeStampFile.getAbsoluteName(), this);
                }
            } catch (Exception e) {
                // Don't stop here
            }
            System.out.println("&&& " + Boolean.toString(timeStamp == null) + ", " + Boolean.toString(date == null));
            submitted = timeStamp != null && date != null;
            findDocuments();
        } catch (SubmissionFolderNotFound sfnf) {
            Tracer.error(sfnf.getMessage());

        } catch (Exception e) {
//        	System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    void findDocuments() {
        Set<String> entryNames = studentFolder.getDescendentEntryNames(feedbackFolder);
        for (String entryName : entryNames) {
            if (Common.isDocumentName(entryName)) {
                documents.add(entryName);
                DocumentFileLoaded.newCase(entryName, this);
            }
        }
    }
//    @Override
//    public void cleanFeedbackFolder() {
//    	if (feedbackFolder ==null)
//    		return;
//    	String name = feedbackFolder.getMixedCaseAbsoluteName();
//    	if (name == null)
//    		return;
//    	try {
//			FileUtils.cleanDirectory(new File(name));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//    }

    @Override
    public void cleanFeedbackFolder() {
        if (feedbackFolder == null) {
            return;
        }
        String name = feedbackFolder.getMixedCaseAbsoluteName();
        if (name == null) {
            return;
        }
        try {
//			FileUtils.cleanDirectory(new File(name));
            File feedbackFile = new File(name);
            File[] children = feedbackFile.listFiles();
            for (File child : children) {
                if (child.isDirectory()) {
                    FileUtils.cleanDirectory(child); // generated directory
                    child.delete();
                } else {
                    if (child.getName().endsWith(".ini")) {
                        continue; // keep desktop.ini file around for git
                    } else {
                        child.delete();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanSubmissionFolder() {
        if (submissionFolder == null) {
            return;
        }
        String name = submissionFolder.getMixedCaseAbsoluteName();
        if (name == null) {
            return;
        }

        try {
            File submissionFile = new File(name);
            File[] children = submissionFile.listFiles();
            for (File child : children) {
                if (child.isDirectory()) {
                    FileUtils.cleanDirectory(child); // generated  directory
                    child.delete();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public String getStudentName() {
        return name;
    }

    public String getOnyen() {
        return onyen;
    }

    public Date getDate() {
        return date;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public FileProxy getSubmissionFolder() {
        return submissionFolder;
    }

    public FileProxy getFeedbackFolder() {
        return feedbackFolder;
    }

    public FileProxy getCommentsFile() {
        return commentsFile;
    }

    @Override
    public String getCommentsFileName() {
        return commentsFileName;
    }

    public FileProxy getTimeStampFile() {
        return timeStampFile;
    }

    public FileProxy getStudentFolder() {
        return studentFolder;
    }

    public List<String> getDocuments() {
        return documents;
    }

    @Override
    public String getStudentDescription() {
        return studentDescription;
    }
}
