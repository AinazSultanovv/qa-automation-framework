
// Статус CI/CD пайплайна (или Enum)
package org.qa.models;

public enum PipelineStatus {

    ALL_PASSED("LOW", 0) {
        @Override
        public String getPipelineAction() {
            return "Trigger deploy & archive report";
        }
    },
    FLAKY_DETECTED("MEDIUM", 2) {
        @Override
        public String getPipelineAction() {
            return "Quarantine test & notify QA lead";
        }
    },
    CRITICAL_FAIL("HIGH", 1) {
        @Override
        public String getPipelineAction() {
            return "Block merge & create Jira bug";
        }
    },
    INFRA_ERROR("CRITICAL", 3) {
        @Override
        public String getPipelineAction() {
            return "Retry pipeline & alert DevOps";
        }
    };

    protected final String severity;
    protected final int defaultRetryCount;

    PipelineStatus(String severity, int defaultRetryCount) {
        this.severity = severity;
        this.defaultRetryCount = defaultRetryCount;
    }

    public String getSeverity() { return severity; }
    public int getDefaultRetryCount() { return defaultRetryCount; }

    public abstract String getPipelineAction();
}