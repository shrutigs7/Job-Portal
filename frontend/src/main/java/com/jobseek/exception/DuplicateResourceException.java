package com.jobseek.exception;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String alreadyAppliedToThisJob) {
        super(alreadyAppliedToThisJob);
    }
}
