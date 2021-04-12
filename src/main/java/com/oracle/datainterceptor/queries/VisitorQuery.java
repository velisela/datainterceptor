package com.oracle.datainterceptor.queries;

import com.oracle.datainterceptor.holders.VisitableDataHolder;

public interface VisitorQuery {
    public void visit(VisitableDataHolder visitable);
}
