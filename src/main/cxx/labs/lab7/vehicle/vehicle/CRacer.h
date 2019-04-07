#pragma once
#include "CPersonImpl.h"
#include "IRacer.h"

class CRacer : public CPersonImpl<IRacer>
{
    const size_t m_awardsCount;

public:
    CRacer(std::string const& name, size_t awardsCount);

    ~CRacer();

    size_t GetAwardsCount() const override;
};
