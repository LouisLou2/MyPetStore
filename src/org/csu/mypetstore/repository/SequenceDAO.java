package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Sequence;

public interface SequenceDAO {
    Sequence getSequence(Sequence sequence);
    void updateSequence(Sequence sequence);
}
