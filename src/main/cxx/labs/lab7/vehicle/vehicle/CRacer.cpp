#include "CRacer.h"

CRacer::CRacer(std::string const& name, size_t awardsCount)
    : CPersonImpl(name)
    , m_awardsCount(awardsCount)
{
}

CRacer::~CRacer() = default;

size_t CRacer::GetAwardsCount() const
{
    return m_awardsCount;
}
