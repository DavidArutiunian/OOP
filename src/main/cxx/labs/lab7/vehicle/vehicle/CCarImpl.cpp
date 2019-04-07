#include "CCarImpl.h"

template <typename Base, typename Passenger>
CCarImpl<Base, Passenger>::CCarImpl(size_t placeCount, ECarModel carModel) : CVehicleImpl(placeCount),
                                                                             m_carModel(carModel)
{
}

template <typename Base, typename Passenger>
CCarImpl<Base, Passenger>::~CCarImpl() = default;

template <typename Base, typename Passenger>
ECarModel CCarImpl<Base, Passenger>::GetCarModel() const
{
    return m_carModel;
}
