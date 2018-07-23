package com.sequenceiq.periscope.monitor.evaluator;

import com.sequenceiq.periscope.monitor.context.EvaluatorContext;

public interface EvaluatorExecutor extends Runnable, EvaluatorContextAware {

    EvaluatorContext getContext();
}
