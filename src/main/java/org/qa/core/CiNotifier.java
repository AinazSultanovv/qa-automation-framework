
// Уведомления CI/ системы
package org.qa.core;

import org.qa.models.PipelineStatus;

public class CiNotifier {

    public static String formatNotification(PipelineStatus status, int actualRetries) {

        return switch (status) {

            case null -> throw new IllegalArgumentException("PipelineStatus cannot be null");

            case ALL_PASSED -> {
                if (actualRetries > status.getDefaultRetryCount()) {
                    throw new IllegalStateException("Max retries exceeded for " + status.name());
                }
                int remaining = status.getDefaultRetryCount() - actualRetries;

                yield String.format("[%s] %s | Action: %s | Retries left: %d",
                        status.getSeverity(),
                        status.name(),
                        status.getPipelineAction(),
                        remaining);
            }
            case FLAKY_DETECTED -> {
                if (actualRetries > status.getDefaultRetryCount()) {
                    throw new IllegalStateException("Max retries exceeded for " + status.name());
                }
                int remaining = status.getDefaultRetryCount() - actualRetries;
                yield String.format("[%s] %s | Action: %s | Retries left: %d",
                        status.getSeverity(),
                        status.name(),
                        status.getPipelineAction(),
                        remaining);
            }
            case CRITICAL_FAIL -> {
                if (actualRetries > status.getDefaultRetryCount()) {
                    throw new IllegalStateException("Max retries exceeded for " + status.name());
                }
                int remaining = status.getDefaultRetryCount() - actualRetries;
                yield String.format("[%s] %s | Action: %s | Retries left: %d",
                        status.getSeverity(),
                        status.name(),
                        status.getPipelineAction(),
                        remaining);
            }
            case INFRA_ERROR -> {
                if (actualRetries > status.getDefaultRetryCount()) {
                    throw new IllegalStateException("Max retries exceeded for " + status.name());
                }
                int remaining = status.getDefaultRetryCount() - actualRetries;
                yield String.format("[%s] %s | Action: %s | Retries left: %d",
                        status.getSeverity(),
                        status.name(),
                        status.getPipelineAction(),
                        remaining);
            }
        };
    }
}