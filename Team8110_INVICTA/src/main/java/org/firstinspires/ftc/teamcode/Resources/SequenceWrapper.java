package org.firstinspires.ftc.teamcode.Resources;

import org.firstinspires.ftc.teamcode.Resources.RoadRunnerQuickstart.trajectorysequence.TrajectorySequenceBuilder;

/**
 * Wrapper object for Roadrunner - uses the builder design pattern object WrapperBuilder
 */
public class SequenceWrapper {
    /**
     * Public object to be referenced later
     */
    public TrajectorySequenceBuilder trajectorySequenceBuilder;

    /**
     * Constructor to assign the trajectorySequenceBuilder
     *
     * @param builder the wrapper for trajectorySequenceBuilder with the new features
     */
    public SequenceWrapper(WrapperBuilder builder) {
        trajectorySequenceBuilder = builder.trajectorySequenceBuilder;

    }

}
