#pragma once
#include "CVehicleImpl.h"
#include "ECarModel.h"

template <typename Base, typename Passenger>
class CCarImpl : public CVehicleImpl<Base, Passenger>
{
    const ECarModel m_carModel;

public:
    CCarImpl(size_t placeCount, ECarModel carModel);

    ~CCarImpl();

    ECarModel GetCarModel() const;
};
