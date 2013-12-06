package net.atos.webtools.tapestry.core.models;

import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jdt.core.IJavaElement;

/**
 * Scheduling rule that will ensure that two jobs will not be executed at the same time (to prevent from locks)
 * 
 * @author mvanbesien
 * @since 1.2
 *
 */
public class FeatureFinderSchedulingRule implements ISchedulingRule {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule#contains(org.eclipse.core.runtime.jobs.ISchedulingRule)
	 */
	@Override
	public boolean contains(ISchedulingRule rule) {
		// If rule is instance of self, check the equality
		if (rule instanceof FeatureFinderSchedulingRule)
			return rule == this;
		// As the referenced job acts on projects, this rule should support all IJavaElements...
		if (rule instanceof IJavaElement)
			return true;
		// If one of case above not supported, returns false.
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.ISchedulingRule#isConflicting(org.eclipse.core.runtime.jobs.ISchedulingRule)
	 */
	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		return rule == this;
	}

}