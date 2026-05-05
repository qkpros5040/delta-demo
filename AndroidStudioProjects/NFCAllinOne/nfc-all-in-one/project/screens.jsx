// NFC All-in-One — Screen components
// Material 3-flavored, dark by default, vibrant green accent
// All screens render inside an AndroidDevice frame

const NFC = (() => {
  // ── Theme tokens ────────────────────────────────────────────
  const dark = {
    bg: '#0e1411',
    surface: '#141b18',
    surfaceHi: '#1a221e',
    surfaceCard: '#1d2622',
    border: 'rgba(255,255,255,0.06)',
    borderStrong: 'rgba(255,255,255,0.10)',
    text: '#e6efe9',
    textMuted: '#8a9994',
    textDim: '#5d6b66',
    accent: '#7af0c4',          // mint/cyan-green
    accentDeep: '#0c8d68',
    accentInk: '#003328',
    danger: '#ff8a7a',
    warn: '#f5d067',
  };
  const light = {
    bg: '#f4f7f5',
    surface: '#ffffff',
    surfaceHi: '#f9fbfa',
    surfaceCard: '#ffffff',
    border: 'rgba(0,0,0,0.06)',
    borderStrong: 'rgba(0,0,0,0.10)',
    text: '#0e1411',
    textMuted: '#5b6864',
    textDim: '#8b958f',
    accent: '#0a8763',
    accentDeep: '#0a8763',
    accentInk: '#ffffff',
    danger: '#c25640',
    warn: '#a47a14',
  };
  const T = (mode) => (mode === 'light' ? light : dark);

  // ── Tiny icon set (line icons, currentColor) ───────────────
  const Icon = ({ name, size = 20, color = 'currentColor', strokeWidth = 1.6 }) => {
    const s = strokeWidth;
    const common = {
      width: size, height: size, viewBox: '0 0 24 24',
      fill: 'none', stroke: color, strokeWidth: s,
      strokeLinecap: 'round', strokeLinejoin: 'round',
    };
    switch (name) {
      case 'wave':
        return (
          <svg {...common}>
            <path d="M4 12c2-3 4-3 6 0s4 3 6 0 4-3 6 0" />
            <path d="M4 17c2-3 4-3 6 0s4 3 6 0 4-3 6 0" />
            <path d="M4 7c2-3 4-3 6 0s4 3 6 0 4-3 6 0" opacity="0.5"/>
          </svg>
        );
      case 'read':
        return (<svg {...common}><path d="M3 12a9 9 0 0 1 18 0"/><path d="M7 12a5 5 0 0 1 10 0"/><circle cx="12" cy="12" r="1.5" fill={color}/></svg>);
      case 'write':
        return (<svg {...common}><path d="M4 20h16"/><path d="M14 4l6 6-9 9H5v-6z"/></svg>);
      case 'clone':
        return (<svg {...common}><rect x="4" y="4" width="11" height="11" rx="2"/><rect x="9" y="9" width="11" height="11" rx="2"/></svg>);
      case 'history':
        return (<svg {...common}><path d="M3 12a9 9 0 1 0 3-6.7L3 8"/><path d="M3 3v5h5"/><path d="M12 7v5l3 2"/></svg>);
      case 'settings':
        return (<svg {...common}><circle cx="12" cy="12" r="3"/><path d="M19.4 15a1.7 1.7 0 0 0 .3 1.8l.1.1a2 2 0 1 1-2.8 2.8l-.1-.1a1.7 1.7 0 0 0-1.8-.3 1.7 1.7 0 0 0-1 1.5V21a2 2 0 1 1-4 0v-.1a1.7 1.7 0 0 0-1.1-1.5 1.7 1.7 0 0 0-1.8.3l-.1.1a2 2 0 1 1-2.8-2.8l.1-.1a1.7 1.7 0 0 0 .3-1.8 1.7 1.7 0 0 0-1.5-1H3a2 2 0 1 1 0-4h.1a1.7 1.7 0 0 0 1.5-1.1 1.7 1.7 0 0 0-.3-1.8l-.1-.1a2 2 0 1 1 2.8-2.8l.1.1a1.7 1.7 0 0 0 1.8.3H9a1.7 1.7 0 0 0 1-1.5V3a2 2 0 1 1 4 0v.1a1.7 1.7 0 0 0 1 1.5 1.7 1.7 0 0 0 1.8-.3l.1-.1a2 2 0 1 1 2.8 2.8l-.1.1a1.7 1.7 0 0 0-.3 1.8V9c.3.6.9 1 1.5 1H21a2 2 0 1 1 0 4h-.1a1.7 1.7 0 0 0-1.5 1z"/></svg>);
      case 'arrow-right':
        return (<svg {...common}><path d="M5 12h14"/><path d="M13 5l7 7-7 7"/></svg>);
      case 'arrow-left':
        return (<svg {...common}><path d="M19 12H5"/><path d="M11 5l-7 7 7 7"/></svg>);
      case 'check':
        return (<svg {...common}><path d="M5 12l5 5L20 7"/></svg>);
      case 'close':
        return (<svg {...common}><path d="M6 6l12 12"/><path d="M18 6L6 18"/></svg>);
      case 'plus':
        return (<svg {...common}><path d="M12 5v14"/><path d="M5 12h14"/></svg>);
      case 'link':
        return (<svg {...common}><path d="M10 14a5 5 0 0 0 7 0l3-3a5 5 0 1 0-7-7l-1 1"/><path d="M14 10a5 5 0 0 0-7 0l-3 3a5 5 0 1 0 7 7l1-1"/></svg>);
      case 'text':
        return (<svg {...common}><path d="M5 7V5h14v2"/><path d="M12 5v14"/><path d="M9 19h6"/></svg>);
      case 'wifi':
        return (<svg {...common}><path d="M2 8.5a17 17 0 0 1 20 0"/><path d="M5 12a13 13 0 0 1 14 0"/><path d="M8.5 15.5a8 8 0 0 1 7 0"/><circle cx="12" cy="19" r="1" fill={color}/></svg>);
      case 'card':
        return (<svg {...common}><rect x="3" y="6" width="18" height="12" rx="2"/><path d="M3 10h18"/></svg>);
      case 'phone':
        return (<svg {...common}><rect x="6" y="2" width="12" height="20" rx="2"/><path d="M11 19h2"/></svg>);
      case 'lock':
        return (<svg {...common}><rect x="4" y="11" width="16" height="10" rx="2"/><path d="M8 11V7a4 4 0 1 1 8 0v4"/></svg>);
      case 'copy':
        return (<svg {...common}><rect x="9" y="9" width="11" height="11" rx="2"/><path d="M5 15V5a2 2 0 0 1 2-2h10"/></svg>);
      case 'share':
        return (<svg {...common}><circle cx="6" cy="12" r="2"/><circle cx="18" cy="6" r="2"/><circle cx="18" cy="18" r="2"/><path d="M8 11l8-4"/><path d="M8 13l8 4"/></svg>);
      case 'star':
        return (<svg {...common}><path d="M12 3l2.7 5.6 6.1.9-4.4 4.3 1 6.1L12 17l-5.4 2.9 1-6.1L3.2 9.5l6.1-.9z"/></svg>);
      case 'star-fill':
        return (<svg width={size} height={size} viewBox="0 0 24 24" fill={color}><path d="M12 3l2.7 5.6 6.1.9-4.4 4.3 1 6.1L12 17l-5.4 2.9 1-6.1L3.2 9.5l6.1-.9z"/></svg>);
      case 'trash':
        return (<svg {...common}><path d="M4 7h16"/><path d="M10 11v6"/><path d="M14 11v6"/><path d="M6 7l1 13a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2l1-13"/><path d="M9 7V4h6v3"/></svg>);
      case 'menu':
        return (<svg {...common}><path d="M4 7h16"/><path d="M4 12h16"/><path d="M4 17h16"/></svg>);
      case 'search':
        return (<svg {...common}><circle cx="11" cy="11" r="7"/><path d="M21 21l-4.3-4.3"/></svg>);
      case 'chevron-right':
        return (<svg {...common}><path d="M9 6l6 6-6 6"/></svg>);
      case 'chevron-down':
        return (<svg {...common}><path d="M6 9l6 6 6-6"/></svg>);
      case 'shield':
        return (<svg {...common}><path d="M12 3l8 3v6c0 4.5-3.4 8.5-8 9-4.6-.5-8-4.5-8-9V6z"/></svg>);
      case 'bolt':
        return (<svg {...common}><path d="M13 2L4 14h7l-1 8 9-12h-7z"/></svg>);
      case 'eye':
        return (<svg {...common}><path d="M2 12s3.5-7 10-7 10 7 10 7-3.5 7-10 7S2 12 2 12z"/><circle cx="12" cy="12" r="3"/></svg>);
      case 'sun':
        return (<svg {...common}><circle cx="12" cy="12" r="4"/><path d="M12 2v2M12 20v2M2 12h2M20 12h2M5 5l1.5 1.5M17.5 17.5L19 19M5 19l1.5-1.5M17.5 6.5L19 5"/></svg>);
      case 'moon':
        return (<svg {...common}><path d="M21 13A9 9 0 1 1 11 3a7 7 0 0 0 10 10z"/></svg>);
      case 'overflow':
        return (<svg {...common}><circle cx="5" cy="12" r="1.4" fill={color}/><circle cx="12" cy="12" r="1.4" fill={color}/><circle cx="19" cy="12" r="1.4" fill={color}/></svg>);
      case 'globe':
        return (<svg {...common}><circle cx="12" cy="12" r="9"/><path d="M3 12h18"/><path d="M12 3a14 14 0 0 1 0 18"/><path d="M12 3a14 14 0 0 0 0 18"/></svg>);
      case 'mail':
        return (<svg {...common}><rect x="3" y="5" width="18" height="14" rx="2"/><path d="M3 7l9 6 9-6"/></svg>);
      case 'pin':
        return (<svg {...common}><path d="M12 2a6 6 0 0 0-6 6c0 5 6 12 6 12s6-7 6-12a6 6 0 0 0-6-6z"/><circle cx="12" cy="8" r="2"/></svg>);
      default:
        return null;
    }
  };

  // ── Scaffold inside AndroidDevice content area ─────────────
  const Screen = ({ t, children, pad = 0, scrollable = true, style }) => (
    <div style={{
      width: '100%', minHeight: '100%', background: t.bg, color: t.text,
      fontFamily: 'Roboto, system-ui, sans-serif',
      padding: pad, boxSizing: 'border-box',
      overflowY: scrollable ? 'auto' : 'hidden',
      ...style,
    }}>{children}</div>
  );

  // Button
  const Btn = ({ t, kind = 'primary', children, icon, full, style }) => {
    const base = {
      display: 'inline-flex', alignItems: 'center', justifyContent: 'center',
      gap: 8, height: 48, borderRadius: 999, padding: '0 22px',
      fontFamily: 'Roboto, system-ui', fontSize: 15, fontWeight: 500,
      letterSpacing: 0.1, cursor: 'pointer', userSelect: 'none',
      width: full ? '100%' : 'auto',
    };
    const s = kind === 'primary' ? {
      background: t.accent, color: t.accentInk,
    } : kind === 'tonal' ? {
      background: 'rgba(122,240,196,0.12)', color: t.accent,
    } : kind === 'outline' ? {
      background: 'transparent', color: t.text, border: `1px solid ${t.borderStrong}`,
    } : kind === 'text' ? {
      background: 'transparent', color: t.accent,
    } : kind === 'danger' ? {
      background: 'rgba(255,138,122,0.12)', color: t.danger,
    } : {};
    return <div style={{ ...base, ...s, ...style }}>{icon && <Icon name={icon} size={18}/>}{children}</div>;
  };

  // Custom app top bar (replaces frame's default)
  const TopBar = ({ t, title, leading = 'menu', trailing = 'overflow', subtitle }) => (
    <div style={{ padding: '8px 4px 4px', background: t.bg }}>
      <div style={{ height: 56, display: 'flex', alignItems: 'center', padding: '0 4px' }}>
        <div style={{ width: 48, height: 48, display: 'flex', alignItems: 'center', justifyContent: 'center', color: t.text }}>
          <Icon name={leading} size={22}/>
        </div>
        <div style={{ flex: 1, fontSize: 22, fontWeight: 500, color: t.text, letterSpacing: -0.2 }}>{title}</div>
        {trailing && <div style={{ width: 48, height: 48, display: 'flex', alignItems: 'center', justifyContent: 'center', color: t.text }}>
          <Icon name={trailing} size={22}/>
        </div>}
      </div>
      {subtitle && <div style={{ padding: '0 16px 12px', color: t.textMuted, fontSize: 13 }}>{subtitle}</div>}
    </div>
  );

  // Section label
  const SectionLabel = ({ t, children, action }) => (
    <div style={{ display: 'flex', alignItems: 'baseline', justifyContent: 'space-between', padding: '20px 20px 10px' }}>
      <div style={{ fontSize: 12, fontWeight: 600, letterSpacing: 1.2, textTransform: 'uppercase', color: t.textMuted }}>{children}</div>
      {action && <div style={{ fontSize: 13, color: t.accent }}>{action}</div>}
    </div>
  );

  // Tag chip
  const Chip = ({ t, label, active, color, icon }) => (
    <div style={{
      display: 'inline-flex', alignItems: 'center', gap: 6, height: 30, padding: '0 12px',
      borderRadius: 999, fontSize: 12, fontWeight: 500,
      background: active ? t.accent : 'transparent',
      color: active ? t.accentInk : (color || t.textMuted),
      border: active ? 'none' : `1px solid ${t.borderStrong}`,
    }}>
      {icon && <Icon name={icon} size={14}/>}
      {label}
    </div>
  );

  // ─────────────────────────────────────────────────────────
  // 1) ONBOARDING
  // ─────────────────────────────────────────────────────────
  const Onboarding = ({ t }) => (
    <Screen t={t} scrollable={false}>
      <div style={{ display: 'flex', flexDirection: 'column', height: '100%', padding: '24px 28px 32px' }}>
        <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: 13, color: t.textMuted }}>
          <div>1 of 3</div>
          <div>Skip</div>
        </div>

        <div style={{ flex: 1, display: 'flex', alignItems: 'center', justifyContent: 'center', position: 'relative', minHeight: 0 }}>
          {/* Concentric ripple — sized to its flex slot only */}
          <div style={{ position: 'relative', width: 280, height: 280, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            {[260, 200, 140, 90].map((s, i) => (
              <div key={i} style={{
                position: 'absolute', width: s, height: s, borderRadius: '50%',
                border: `1px solid ${t.accent}`, opacity: 0.08 + i * 0.05,
              }}/>
            ))}
            <div style={{
              width: 120, height: 120, borderRadius: '50%',
              background: `radial-gradient(circle at 30% 30%, ${t.accent}, ${t.accentDeep})`,
              display: 'flex', alignItems: 'center', justifyContent: 'center',
              boxShadow: `0 20px 60px ${t.accent}40`,
              color: t.accentInk,
            }}>
              <Icon name="wave" size={56}/>
            </div>
          </div>
        </div>

        <div>
          <div style={{ fontSize: 32, fontWeight: 500, lineHeight: 1.1, letterSpacing: -0.5, marginBottom: 16, textWrap: 'pretty' }}>
            One tap.<br/>Every NFC tag.
          </div>
          <div style={{ fontSize: 15, color: t.textMuted, lineHeight: 1.5, marginBottom: 28, textWrap: 'pretty' }}>
            Read, write, and clone NFC chips with a single, gorgeous app. Works with NDEF, MIFARE, NTAG and more.
          </div>

          <div style={{ display: 'flex', gap: 6, marginBottom: 24 }}>
            <div style={{ flex: 2, height: 4, borderRadius: 2, background: t.accent }}/>
            <div style={{ flex: 1, height: 4, borderRadius: 2, background: t.borderStrong }}/>
            <div style={{ flex: 1, height: 4, borderRadius: 2, background: t.borderStrong }}/>
          </div>

          <Btn t={t} full icon="arrow-right">Get started</Btn>
        </div>
      </div>
    </Screen>
  );

  // ─────────────────────────────────────────────────────────
  // 2) HOME / DASHBOARD
  // ─────────────────────────────────────────────────────────
  const Home = ({ t, density = 'comfortable' }) => {
    const tight = density === 'compact';
    const tile = (icon, title, sub, primary) => (
      <div style={{
        background: primary ? t.accent : t.surfaceCard,
        color: primary ? t.accentInk : t.text,
        borderRadius: 22,
        border: primary ? 'none' : `1px solid ${t.border}`,
        padding: tight ? '14px 14px' : '18px 16px',
        minHeight: tight ? 96 : 116,
        display: 'flex', flexDirection: 'column', justifyContent: 'space-between',
      }}>
        <Icon name={icon} size={22}/>
        <div>
          <div style={{ fontSize: 16, fontWeight: 500 }}>{title}</div>
          <div style={{ fontSize: 12, opacity: 0.7, marginTop: 2 }}>{sub}</div>
        </div>
      </div>
    );

    return (
      <Screen t={t}>
        {/* Header */}
        <div style={{ padding: '14px 20px 8px' }}>
          <div style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between' }}>
            <div>
              <div style={{ fontSize: 13, color: t.textMuted }}>Good evening, Alex</div>
              <div style={{ fontSize: 22, fontWeight: 500, marginTop: 2, letterSpacing: -0.2 }}>NFC All-in-One</div>
            </div>
            <div style={{
              width: 40, height: 40, borderRadius: '50%',
              background: `linear-gradient(135deg, ${t.accent}, ${t.accentDeep})`,
              display: 'flex', alignItems: 'center', justifyContent: 'center',
              fontWeight: 600, color: t.accentInk, fontSize: 14,
            }}>AP</div>
          </div>
        </div>

        {/* Hero scan card */}
        <div style={{ padding: '14px 20px 4px' }}>
          <div style={{
            position: 'relative', borderRadius: 28, overflow: 'hidden',
            background: `linear-gradient(155deg, ${t.surfaceCard} 0%, ${t.surface} 60%)`,
            border: `1px solid ${t.border}`,
            padding: '22px 20px',
          }}>
            {/* ambient ring */}
            <div style={{
              position: 'absolute', right: -60, top: -60, width: 220, height: 220, borderRadius: '50%',
              background: `radial-gradient(circle, ${t.accent}33, transparent 70%)`, filter: 'blur(2px)',
            }}/>
            <div style={{ position: 'relative' }}>
              <div style={{ display: 'inline-flex', alignItems: 'center', gap: 6,
                background: 'rgba(122,240,196,0.12)', color: t.accent,
                padding: '4px 10px', borderRadius: 999, fontSize: 11, fontWeight: 600, letterSpacing: 0.6, textTransform: 'uppercase',
              }}>
                <span style={{ width: 6, height: 6, borderRadius: '50%', background: t.accent, boxShadow: `0 0 8px ${t.accent}` }}/>
                NFC ready
              </div>
              <div style={{ fontSize: 24, fontWeight: 500, marginTop: 14, lineHeight: 1.15, letterSpacing: -0.3 }}>
                Hold a tag to the back<br/>of your phone
              </div>
              <div style={{ fontSize: 13, color: t.textMuted, marginTop: 8 }}>
                Reader is listening · auto-detect on
              </div>

              <div style={{ marginTop: 20, display: 'flex', gap: 10 }}>
                <Btn t={t} icon="read">Start reading</Btn>
                <Btn t={t} kind="outline" icon="bolt">Quick scan</Btn>
              </div>
            </div>
          </div>
        </div>

        {/* Action grid */}
        <SectionLabel t={t}>What do you want to do</SectionLabel>
        <div style={{ padding: '0 20px', display: 'grid', gridTemplateColumns: '1fr 1fr', gap: 12 }}>
          {tile('read', 'Read', 'Scan & decode any tag')}
          {tile('write', 'Write', 'Compose NDEF payload')}
          {tile('clone', 'Clone', 'Source → target', true)}
          {tile('shield', 'Verify', 'Check authenticity')}
        </div>

        {/* Recent */}
        <SectionLabel t={t} action="See all">Recent activity</SectionLabel>
        <div style={{ padding: '0 16px 24px', display: 'flex', flexDirection: 'column', gap: 8 }}>
          {[
            { name: 'apartment-key.ndef', kind: 'NTAG215 · URL', ago: '2m ago', i: 'link' },
            { name: 'office wifi guest', kind: 'NTAG213 · Wi-Fi', ago: '1h ago', i: 'wifi' },
            { name: 'transit card #04', kind: 'MIFARE Classic', ago: 'Yesterday', i: 'card' },
          ].map((r, i) => (
            <div key={i} style={{
              display: 'flex', alignItems: 'center', gap: 14, padding: '12px 14px',
              background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 16,
            }}>
              <div style={{
                width: 38, height: 38, borderRadius: 12,
                background: 'rgba(122,240,196,0.10)', color: t.accent,
                display: 'flex', alignItems: 'center', justifyContent: 'center',
              }}>
                <Icon name={r.i} size={18}/>
              </div>
              <div style={{ flex: 1, minWidth: 0 }}>
                <div style={{ fontSize: 14, fontWeight: 500, whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>{r.name}</div>
                <div style={{ fontSize: 12, color: t.textMuted }}>{r.kind} · {r.ago}</div>
              </div>
              <Icon name="chevron-right" size={18} color={t.textDim}/>
            </div>
          ))}
        </div>

        {/* Bottom nav */}
        <BottomNav t={t} active="home"/>
      </Screen>
    );
  };

  // Bottom nav
  const BottomNav = ({ t, active = 'home' }) => {
    const items = [
      { id: 'home', label: 'Home', icon: 'wave' },
      { id: 'read', label: 'Read', icon: 'read' },
      { id: 'write', label: 'Write', icon: 'write' },
      { id: 'history', label: 'History', icon: 'history' },
    ];
    return (
      <div style={{
        position: 'sticky', bottom: 0, marginTop: 8,
        background: t.surface, borderTop: `1px solid ${t.border}`,
        padding: '6px 4px 8px', display: 'flex',
      }}>
        {items.map(it => {
          const on = it.id === active;
          return (
            <div key={it.id} style={{
              flex: 1, display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 4, padding: '6px 0',
              color: on ? t.text : t.textMuted,
            }}>
              <div style={{
                height: 28, padding: '0 18px', borderRadius: 999,
                background: on ? 'rgba(122,240,196,0.16)' : 'transparent',
                color: on ? t.accent : t.textMuted,
                display: 'flex', alignItems: 'center', justifyContent: 'center',
              }}>
                <Icon name={it.icon} size={20}/>
              </div>
              <div style={{ fontSize: 11, fontWeight: 500 }}>{it.label}</div>
            </div>
          );
        })}
      </div>
    );
  };

  // ─────────────────────────────────────────────────────────
  // 3) READ — scanning state with radar/ripple
  // ─────────────────────────────────────────────────────────
  const ReadScanning = ({ t, animation = 'radar' }) => (
    <Screen t={t} scrollable={false}>
      <TopBar t={t} title="Read tag" leading="arrow-left" trailing="settings"/>

      <div style={{ padding: '0 20px', display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center' }}>
        <div style={{ fontSize: 26, fontWeight: 500, letterSpacing: -0.3, marginTop: 8 }}>Tap a tag</div>
        <div style={{ fontSize: 14, color: t.textMuted, marginTop: 6 }}>Hold tag to the back of your phone</div>
      </div>

      {/* Animation surface */}
      <div style={{ position: 'relative', height: 340, margin: '20px 24px 0',
        borderRadius: 28, overflow: 'hidden',
        background: `radial-gradient(circle at 50% 65%, ${t.surfaceCard}, ${t.surface})`,
        border: `1px solid ${t.border}`,
      }}>
        {animation === 'radar' && <>
          {[280, 220, 160, 100].map((s, i) => (
            <div key={i} style={{
              position: 'absolute', left: '50%', top: '60%', transform: 'translate(-50%, -50%)',
              width: s, height: s, borderRadius: '50%',
              border: `1.5px solid ${t.accent}`,
              opacity: 0.5 - i * 0.1,
            }}/>
          ))}
          <div style={{
            position: 'absolute', left: '50%', top: '60%', transform: 'translate(-50%, -50%)',
            width: 64, height: 64, borderRadius: '50%',
            background: t.accent, color: t.accentInk,
            display: 'flex', alignItems: 'center', justifyContent: 'center',
            boxShadow: `0 0 40px ${t.accent}80`,
          }}><Icon name="wave" size={32}/></div>
        </>}
        {animation === 'wave' && <>
          <svg viewBox="0 0 320 200" style={{ position: 'absolute', left: 0, right: 0, top: '40%', width: '100%' }}>
            {[0, 1, 2].map(i => (
              <path key={i} d={`M0 100 Q 40 ${60 + i*20} 80 100 T 160 100 T 240 100 T 320 100`}
                stroke={t.accent} strokeWidth="2" fill="none" opacity={0.8 - i * 0.25}/>
            ))}
          </svg>
        </>}

        {/* Phone hint */}
        <div style={{
          position: 'absolute', left: 24, bottom: 16, right: 24,
          display: 'flex', alignItems: 'center', gap: 12,
          background: 'rgba(0,0,0,0.25)', borderRadius: 16, padding: '12px 14px',
          backdropFilter: 'blur(6px)',
        }}>
          <div style={{
            width: 36, height: 36, borderRadius: 10,
            background: 'rgba(122,240,196,0.14)', color: t.accent,
            display: 'flex', alignItems: 'center', justifyContent: 'center',
          }}><Icon name="phone" size={18}/></div>
          <div style={{ flex: 1, textAlign: 'left' }}>
            <div style={{ fontSize: 13, fontWeight: 500 }}>Listening for NFC…</div>
            <div style={{ fontSize: 11, color: t.textMuted }}>Auto-cancel in 30s</div>
          </div>
        </div>
      </div>

      {/* Recent tags strip */}
      <SectionLabel t={t}>Last scanned</SectionLabel>
      <div style={{ padding: '0 20px', display: 'flex', gap: 10, overflowX: 'auto' }}>
        {['Apt key', 'Wifi guest', 'Transit', 'Sticker #4'].map((n, i) => (
          <div key={i} style={{
            flexShrink: 0, padding: '10px 14px', borderRadius: 14,
            background: t.surfaceCard, border: `1px solid ${t.border}`,
            display: 'flex', alignItems: 'center', gap: 8,
          }}>
            <div style={{ width: 6, height: 6, borderRadius: '50%', background: t.accent }}/>
            <div style={{ fontSize: 12 }}>{n}</div>
          </div>
        ))}
      </div>

      <div style={{ padding: '24px 20px 12px', display: 'flex', gap: 10 }}>
        <Btn t={t} kind="outline" full>Cancel</Btn>
        <Btn t={t} kind="tonal" full icon="bolt">Manual</Btn>
      </div>
    </Screen>
  );

  // ─────────────────────────────────────────────────────────
  // 4) READ RESULT — tag detail after scan
  // ─────────────────────────────────────────────────────────
  const ReadResult = ({ t }) => (
    <Screen t={t}>
      <TopBar t={t} title="Tag detected" leading="arrow-left" trailing="overflow"/>

      {/* Success banner */}
      <div style={{ padding: '0 20px' }}>
        <div style={{
          background: `linear-gradient(140deg, ${t.accent}25, ${t.accent}05)`,
          border: `1px solid ${t.accent}30`,
          borderRadius: 22, padding: '18px 18px',
          display: 'flex', alignItems: 'center', gap: 14,
        }}>
          <div style={{
            width: 44, height: 44, borderRadius: '50%', background: t.accent, color: t.accentInk,
            display: 'flex', alignItems: 'center', justifyContent: 'center',
          }}><Icon name="check" size={22} strokeWidth={2.4}/></div>
          <div style={{ flex: 1 }}>
            <div style={{ fontSize: 16, fontWeight: 500 }}>Read complete</div>
            <div style={{ fontSize: 12, color: t.textMuted, marginTop: 2 }}>1 NDEF record · 137 bytes used of 504</div>
          </div>
        </div>
      </div>

      {/* Tag identity card */}
      <div style={{ padding: '14px 20px 0' }}>
        <div style={{
          background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 22,
          padding: '16px 18px',
        }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'flex-start' }}>
            <div>
              <div style={{ fontSize: 11, color: t.textMuted, fontWeight: 600, letterSpacing: 1, textTransform: 'uppercase' }}>Tag</div>
              <div style={{ fontSize: 20, fontWeight: 500, marginTop: 4, letterSpacing: -0.2 }}>NTAG215</div>
              <div style={{ fontSize: 12, color: t.textMuted, marginTop: 2 }}>NFC Forum Type 2 · ISO 14443-3A</div>
            </div>
            <Chip t={t} label="Writable" icon="check" color={t.accent}/>
          </div>
          <div style={{ height: 1, background: t.border, margin: '14px -2px' }}/>
          <div style={{ display: 'grid', gridTemplateColumns: '1fr 1fr', gap: 12 }}>
            {[
              ['UID', '04:A2:9F:E2:6C:51:80'],
              ['Capacity', '504 bytes'],
              ['ATQA / SAK', '0x0044 / 0x00'],
              ['Read-only', 'No'],
            ].map(([k, v]) => (
              <div key={k}>
                <div style={{ fontSize: 11, color: t.textMuted }}>{k}</div>
                <div style={{ fontSize: 13, fontFamily: 'ui-monospace, Menlo, monospace', marginTop: 2 }}>{v}</div>
              </div>
            ))}
          </div>
        </div>
      </div>

      {/* Records */}
      <SectionLabel t={t}>Records</SectionLabel>
      <div style={{ padding: '0 20px' }}>
        <div style={{
          background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 18,
          padding: '14px 14px',
        }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: 10, marginBottom: 10 }}>
            <div style={{ width: 32, height: 32, borderRadius: 10, background: 'rgba(122,240,196,0.14)', color: t.accent, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
              <Icon name="link" size={16}/>
            </div>
            <div style={{ flex: 1 }}>
              <div style={{ fontSize: 13, fontWeight: 500 }}>URI · https</div>
              <div style={{ fontSize: 11, color: t.textMuted }}>Record 1 of 1 · 134 B</div>
            </div>
            <Icon name="copy" size={18} color={t.textMuted}/>
          </div>
          <div style={{
            background: t.bg, borderRadius: 12, padding: '12px 14px',
            fontFamily: 'ui-monospace, Menlo, monospace', fontSize: 13, wordBreak: 'break-all',
            color: t.text, lineHeight: 1.5,
            border: `1px solid ${t.border}`,
          }}>
            https://nfc-allinone.app/p/8d2a9?room=204&issued=2026-05-04
          </div>
        </div>
      </div>

      {/* Raw / hex */}
      <SectionLabel t={t} action="Show all bytes">Raw payload</SectionLabel>
      <div style={{ padding: '0 20px 12px' }}>
        <div style={{
          background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 18,
          padding: '14px', fontFamily: 'ui-monospace, Menlo, monospace',
          fontSize: 11.5, color: t.textMuted, lineHeight: 1.6, letterSpacing: 0.3,
        }}>
          <div><span style={{ color: t.accent }}>00</span>  D1 01 7F 55 04 6E 66 63 2D 61 6C 6C</div>
          <div><span style={{ color: t.accent }}>10</span>  69 6E 6F 6E 65 2E 61 70 70 2F 70 2F</div>
          <div><span style={{ color: t.accent }}>20</span>  38 64 32 61 39 3F 72 6F 6F 6D 3D 32</div>
          <div><span style={{ color: t.accent }}>30</span>  30 34 26 69 73 73 75 65 64 3D 32 30</div>
          <div><span style={{ color: t.textDim }}>… 9 more rows</span></div>
        </div>
      </div>

      {/* Actions */}
      <div style={{ padding: '8px 20px 24px', display: 'grid', gridTemplateColumns: '1fr 1fr', gap: 10 }}>
        <Btn t={t} icon="copy">Copy URL</Btn>
        <Btn t={t} kind="outline" icon="write">Write to new</Btn>
        <Btn t={t} kind="tonal" icon="clone">Clone tag</Btn>
        <Btn t={t} kind="outline" icon="star">Save</Btn>
      </div>
    </Screen>
  );

  // ─────────────────────────────────────────────────────────
  // 5) WRITE — compose payload
  // ─────────────────────────────────────────────────────────
  const Write = ({ t }) => {
    const types = [
      { id: 'url', label: 'URL', icon: 'link' },
      { id: 'text', label: 'Text', icon: 'text' },
      { id: 'wifi', label: 'Wi-Fi', icon: 'wifi' },
      { id: 'vcard', label: 'Contact', icon: 'mail' },
      { id: 'app', label: 'App', icon: 'phone' },
      { id: 'geo', label: 'Location', icon: 'pin' },
    ];
    return (
      <Screen t={t}>
        <TopBar t={t} title="Write tag" leading="arrow-left" trailing="overflow"/>

        <SectionLabel t={t}>Payload type</SectionLabel>
        <div style={{ padding: '0 16px', display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: 10 }}>
          {types.map((tp, i) => {
            const active = tp.id === 'url';
            return (
              <div key={tp.id} style={{
                background: active ? 'rgba(122,240,196,0.10)' : t.surfaceCard,
                border: `1px solid ${active ? t.accent + '60' : t.border}`,
                borderRadius: 16, padding: '14px 10px',
                display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 8,
                color: active ? t.accent : t.text,
              }}>
                <Icon name={tp.icon} size={20}/>
                <div style={{ fontSize: 12, fontWeight: 500 }}>{tp.label}</div>
              </div>
            );
          })}
        </div>

        {/* Editor */}
        <SectionLabel t={t}>URL</SectionLabel>
        <div style={{ padding: '0 20px' }}>
          <div style={{
            background: t.surfaceCard, border: `1px solid ${t.accent}40`, borderRadius: 16,
            padding: '14px 16px',
          }}>
            <div style={{ fontSize: 11, color: t.accent, fontWeight: 600, letterSpacing: 0.6 }}>URI</div>
            <div style={{
              fontFamily: 'ui-monospace, Menlo, monospace', fontSize: 14,
              marginTop: 4, color: t.text, wordBreak: 'break-all',
            }}>
              https://nfc-allinone.app/p/<span style={{ color: t.accent }}>welcome</span>
              <span style={{ display: 'inline-block', width: 2, height: 14, background: t.accent, marginLeft: 2, verticalAlign: 'middle' }}/>
            </div>
          </div>
        </div>

        {/* Options */}
        <SectionLabel t={t}>Options</SectionLabel>
        <div style={{ padding: '0 20px', display: 'flex', flexDirection: 'column', gap: 10 }}>
          <Toggle t={t} label="Lock tag after write" sub="Make this tag read-only forever" icon="lock" on={false}/>
          <Toggle t={t} label="Add Android app launcher" sub="Open app if installed" icon="phone" on/>
          <Toggle t={t} label="Compress payload" sub="Shorten with internal redirect" icon="bolt" on={false}/>
        </div>

        {/* Capacity meter */}
        <div style={{ padding: '20px 20px 0' }}>
          <div style={{ display: 'flex', justifyContent: 'space-between', fontSize: 12, color: t.textMuted, marginBottom: 6 }}>
            <span>Payload size</span>
            <span style={{ color: t.text }}>72 / 504 B</span>
          </div>
          <div style={{ height: 6, borderRadius: 3, background: t.borderStrong, overflow: 'hidden' }}>
            <div style={{ width: '14%', height: '100%', background: t.accent }}/>
          </div>
        </div>

        <div style={{ padding: '20px 20px 16px' }}>
          <Btn t={t} full icon="write">Continue to write</Btn>
        </div>

        <BottomNav t={t} active="write"/>
      </Screen>
    );
  };

  const Toggle = ({ t, label, sub, icon, on }) => (
    <div style={{
      display: 'flex', alignItems: 'center', gap: 14,
      background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 16,
      padding: '12px 14px',
    }}>
      <div style={{
        width: 36, height: 36, borderRadius: 10,
        background: 'rgba(122,240,196,0.10)', color: t.accent,
        display: 'flex', alignItems: 'center', justifyContent: 'center',
      }}><Icon name={icon} size={18}/></div>
      <div style={{ flex: 1 }}>
        <div style={{ fontSize: 14, fontWeight: 500 }}>{label}</div>
        <div style={{ fontSize: 12, color: t.textMuted }}>{sub}</div>
      </div>
      <div style={{
        width: 44, height: 26, borderRadius: 999,
        background: on ? t.accent : t.borderStrong,
        position: 'relative', flexShrink: 0,
      }}>
        <div style={{
          position: 'absolute', top: 3, left: on ? 22 : 3,
          width: 20, height: 20, borderRadius: '50%',
          background: on ? t.accentInk : t.text,
        }}/>
      </div>
    </div>
  );

  // ─────────────────────────────────────────────────────────
  // 6) CLONE — source → target dual flow
  // ─────────────────────────────────────────────────────────
  const Clone = ({ t }) => (
    <Screen t={t}>
      <TopBar t={t} title="Clone tag" leading="arrow-left" trailing="overflow"/>

      {/* Steps */}
      <div style={{ padding: '4px 20px 16px', display: 'flex', alignItems: 'center', gap: 8 }}>
        {['Source', 'Verify', 'Target'].map((s, i) => (
          <React.Fragment key={s}>
            <div style={{
              flex: 1, padding: '6px 0', textAlign: 'center', borderRadius: 999,
              background: i === 1 ? t.accent : i === 0 ? 'rgba(122,240,196,0.14)' : t.surfaceCard,
              color: i === 1 ? t.accentInk : i === 0 ? t.accent : t.textMuted,
              fontSize: 12, fontWeight: 500,
              border: i === 2 ? `1px solid ${t.border}` : 'none',
            }}>{i + 1}. {s}</div>
          </React.Fragment>
        ))}
      </div>

      {/* Source card (filled) */}
      <div style={{ padding: '0 20px' }}>
        <div style={{
          background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 22,
          padding: '16px 18px', position: 'relative',
        }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: 8, marginBottom: 10 }}>
            <span style={{ fontSize: 11, fontWeight: 600, color: t.accent, letterSpacing: 1, textTransform: 'uppercase' }}>Source</span>
            <Icon name="check" size={14} color={t.accent}/>
          </div>
          <div style={{ fontSize: 17, fontWeight: 500 }}>NTAG215 · apartment-key</div>
          <div style={{ fontSize: 12, color: t.textMuted, marginTop: 4, fontFamily: 'ui-monospace, Menlo, monospace' }}>
            04:A2:9F:E2:6C:51:80
          </div>
          <div style={{ display: 'flex', gap: 6, marginTop: 12 }}>
            <Chip t={t} label="1 record" />
            <Chip t={t} label="URL" icon="link"/>
            <Chip t={t} label="137 B"/>
          </div>
        </div>
      </div>

      {/* Connector */}
      <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', padding: '14px 0', position: 'relative' }}>
        <div style={{ width: 2, height: 28, background: `linear-gradient(${t.accent}, transparent)` }}/>
        <div style={{
          position: 'absolute', top: '50%', transform: 'translateY(-50%)',
          width: 36, height: 36, borderRadius: '50%', background: t.accent, color: t.accentInk,
          display: 'flex', alignItems: 'center', justifyContent: 'center',
        }}>
          <Icon name="arrow-right" size={18} strokeWidth={2.2}/>
        </div>
      </div>

      {/* Target card (waiting) */}
      <div style={{ padding: '0 20px' }}>
        <div style={{
          background: t.surfaceCard, border: `2px dashed ${t.accent}50`, borderRadius: 22,
          padding: '20px 18px', textAlign: 'center',
          display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 10,
        }}>
          <div style={{ fontSize: 11, fontWeight: 600, color: t.textMuted, letterSpacing: 1, textTransform: 'uppercase' }}>Target</div>
          <div style={{
            width: 64, height: 64, borderRadius: '50%',
            background: 'rgba(122,240,196,0.10)', color: t.accent,
            display: 'flex', alignItems: 'center', justifyContent: 'center',
            position: 'relative',
          }}>
            <Icon name="wave" size={28}/>
            <div style={{
              position: 'absolute', inset: -6, borderRadius: '50%',
              border: `1.5px solid ${t.accent}40`,
            }}/>
            <div style={{
              position: 'absolute', inset: -16, borderRadius: '50%',
              border: `1.5px solid ${t.accent}20`,
            }}/>
          </div>
          <div style={{ fontSize: 16, fontWeight: 500 }}>Hold target tag now</div>
          <div style={{ fontSize: 12, color: t.textMuted, maxWidth: 240, lineHeight: 1.5 }}>
            Place a blank, writable tag against your phone. We'll verify capacity & write 137 bytes.
          </div>
        </div>
      </div>

      {/* Compatibility */}
      <SectionLabel t={t}>Compatibility</SectionLabel>
      <div style={{ padding: '0 20px 16px' }}>
        <div style={{
          background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 16,
          padding: '12px 14px', display: 'flex', flexDirection: 'column', gap: 8,
        }}>
          {[
            ['NDEF copy', true],
            ['UID spoofing', false, 'Requires special tag'],
            ['Locked sectors', null, 'Skipped'],
          ].map(([k, ok, note], i) => (
            <div key={i} style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
              <div style={{
                width: 18, height: 18, borderRadius: '50%',
                background: ok === true ? t.accent : ok === false ? 'rgba(255,138,122,0.16)' : t.borderStrong,
                color: ok === true ? t.accentInk : ok === false ? t.danger : t.textMuted,
                display: 'flex', alignItems: 'center', justifyContent: 'center', fontSize: 11,
              }}>
                {ok === true ? '✓' : ok === false ? '✕' : '·'}
              </div>
              <div style={{ flex: 1, fontSize: 13 }}>{k}</div>
              {note && <div style={{ fontSize: 11, color: t.textMuted }}>{note}</div>}
            </div>
          ))}
        </div>
      </div>

      <div style={{ padding: '0 20px 20px', display: 'flex', gap: 10 }}>
        <Btn t={t} kind="outline" full>Cancel</Btn>
        <Btn t={t} full icon="clone">Begin clone</Btn>
      </div>
    </Screen>
  );

  // ─────────────────────────────────────────────────────────
  // 7) HISTORY
  // ─────────────────────────────────────────────────────────
  const History = ({ t }) => {
    const items = [
      { name: 'apartment-key.ndef', kind: 'NTAG215 · URL', ago: '2m ago', icon: 'link', tag: 'read', star: true },
      { name: 'office wifi guest', kind: 'NTAG213 · Wi-Fi', ago: '1h ago', icon: 'wifi', tag: 'write' },
      { name: 'transit card #04', kind: 'MIFARE Classic 1K', ago: 'Yesterday', icon: 'card', tag: 'read' },
      { name: 'business-card.vcf', kind: 'NTAG216 · vCard', ago: 'Mon', icon: 'mail', tag: 'write', star: true },
      { name: 'home-router cloned', kind: 'NTAG215 → NTAG215', ago: 'Apr 28', icon: 'clone', tag: 'clone' },
      { name: 'sticker #4', kind: 'NTAG213 · Text', ago: 'Apr 27', icon: 'text', tag: 'read' },
    ];
    const tagColor = (k) => k === 'read' ? t.accent : k === 'write' ? '#9bc4ff' : k === 'clone' ? '#f5c87a' : t.textMuted;
    return (
      <Screen t={t}>
        <TopBar t={t} title="History" leading="arrow-left" trailing="search"/>

        {/* Filter chips */}
        <div style={{ padding: '0 20px 4px', display: 'flex', gap: 8, overflowX: 'auto' }}>
          {['All', 'Read', 'Write', 'Clone', 'Saved', 'NDEF', 'MIFARE'].map((f, i) => (
            <Chip key={f} t={t} label={f} active={i === 0}/>
          ))}
        </div>

        <SectionLabel t={t} action="Edit">Today</SectionLabel>
        <div style={{ padding: '0 16px', display: 'flex', flexDirection: 'column', gap: 8 }}>
          {items.slice(0, 2).map((r, i) => (
            <HistoryRow key={i} t={t} r={r} tagColor={tagColor}/>
          ))}
        </div>

        <SectionLabel t={t}>Earlier this week</SectionLabel>
        <div style={{ padding: '0 16px 12px', display: 'flex', flexDirection: 'column', gap: 8 }}>
          {items.slice(2).map((r, i) => (
            <HistoryRow key={i} t={t} r={r} tagColor={tagColor}/>
          ))}
        </div>

        {/* FAB */}
        <div style={{
          position: 'sticky', bottom: 88, marginRight: 20, marginTop: -52,
          marginLeft: 'auto', width: 'fit-content',
        }}>
          <div style={{
            background: t.accent, color: t.accentInk,
            height: 56, padding: '0 22px', borderRadius: 18,
            display: 'flex', alignItems: 'center', gap: 8, fontWeight: 500,
            boxShadow: `0 12px 32px ${t.accent}40`,
          }}>
            <Icon name="plus" size={20} strokeWidth={2.4}/>
            New scan
          </div>
        </div>

        <BottomNav t={t} active="history"/>
      </Screen>
    );
  };

  const HistoryRow = ({ t, r, tagColor }) => (
    <div style={{
      display: 'flex', alignItems: 'center', gap: 12, padding: '12px 14px',
      background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 16,
    }}>
      <div style={{
        width: 38, height: 38, borderRadius: 12,
        background: 'rgba(122,240,196,0.10)', color: t.accent,
        display: 'flex', alignItems: 'center', justifyContent: 'center',
      }}>
        <Icon name={r.icon} size={18}/>
      </div>
      <div style={{ flex: 1, minWidth: 0 }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: 6 }}>
          <div style={{ fontSize: 14, fontWeight: 500, whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>{r.name}</div>
          {r.star && <Icon name="star-fill" size={12} color={t.warn}/>}
        </div>
        <div style={{ fontSize: 11.5, color: t.textMuted, marginTop: 2 }}>{r.kind} · {r.ago}</div>
      </div>
      <div style={{
        fontSize: 10, fontWeight: 600, letterSpacing: 0.6, textTransform: 'uppercase',
        color: tagColor(r.tag), padding: '4px 8px', borderRadius: 6,
        background: 'rgba(255,255,255,0.04)',
      }}>{r.tag}</div>
    </div>
  );

  // ─────────────────────────────────────────────────────────
  // 8) SETTINGS
  // ─────────────────────────────────────────────────────────
  const Settings = ({ t }) => {
    const Row = ({ icon, label, sub, value, danger }) => (
      <div style={{ display: 'flex', alignItems: 'center', gap: 14, padding: '14px 16px' }}>
        <div style={{
          width: 36, height: 36, borderRadius: 10,
          background: danger ? 'rgba(255,138,122,0.10)' : 'rgba(122,240,196,0.08)',
          color: danger ? t.danger : t.accent,
          display: 'flex', alignItems: 'center', justifyContent: 'center',
        }}><Icon name={icon} size={18}/></div>
        <div style={{ flex: 1 }}>
          <div style={{ fontSize: 14, fontWeight: 500, color: danger ? t.danger : t.text }}>{label}</div>
          {sub && <div style={{ fontSize: 12, color: t.textMuted, marginTop: 2 }}>{sub}</div>}
        </div>
        {typeof value === 'string' ? (
          <div style={{ fontSize: 13, color: t.textMuted }}>{value}</div>
        ) : value === true ? (
          <div style={{ width: 44, height: 26, borderRadius: 999, background: t.accent, position: 'relative' }}>
            <div style={{ position: 'absolute', top: 3, left: 22, width: 20, height: 20, borderRadius: '50%', background: t.accentInk }}/>
          </div>
        ) : value === false ? (
          <div style={{ width: 44, height: 26, borderRadius: 999, background: t.borderStrong, position: 'relative' }}>
            <div style={{ position: 'absolute', top: 3, left: 3, width: 20, height: 20, borderRadius: '50%', background: t.text }}/>
          </div>
        ) : (
          <Icon name="chevron-right" size={18} color={t.textDim}/>
        )}
      </div>
    );
    const Group = ({ children }) => (
      <div style={{
        margin: '0 16px', background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 18,
        overflow: 'hidden',
      }}>
        {React.Children.map(children, (child, i) => (
          <>
            {i > 0 && <div style={{ height: 1, background: t.border, marginLeft: 64 }}/>}
            {child}
          </>
        ))}
      </div>
    );

    return (
      <Screen t={t}>
        <TopBar t={t} title="Settings" leading="arrow-left" trailing={null}/>

        {/* Profile card */}
        <div style={{ padding: '0 16px 4px' }}>
          <div style={{
            background: `linear-gradient(135deg, ${t.accent}25, ${t.surfaceCard})`,
            border: `1px solid ${t.border}`, borderRadius: 20,
            padding: '16px 18px', display: 'flex', alignItems: 'center', gap: 14,
          }}>
            <div style={{
              width: 52, height: 52, borderRadius: '50%',
              background: `linear-gradient(135deg, ${t.accent}, ${t.accentDeep})`,
              display: 'flex', alignItems: 'center', justifyContent: 'center',
              fontWeight: 600, color: t.accentInk, fontSize: 18,
            }}>AP</div>
            <div style={{ flex: 1 }}>
              <div style={{ fontSize: 16, fontWeight: 500 }}>Alex Park</div>
              <div style={{ fontSize: 12, color: t.textMuted }}>Pro · 2 years · 318 tags</div>
            </div>
            <Icon name="chevron-right" size={20} color={t.textDim}/>
          </div>
        </div>

        <SectionLabel t={t}>NFC</SectionLabel>
        <Group>
          <Row icon="bolt" label="Auto-scan on app open" sub="Listen for tags immediately" value={true}/>
          <Row icon="eye" label="Show raw bytes" sub="Power-user view" value={true}/>
          <Row icon="shield" label="Verify before write" value={false}/>
          <Row icon="lock" label="Default lock policy" value="Ask each time"/>
        </Group>

        <SectionLabel t={t}>Appearance</SectionLabel>
        <Group>
          <Row icon="moon" label="Theme" value="System"/>
          <Row icon="star" label="Accent color" value="Mint"/>
        </Group>

        <SectionLabel t={t}>Privacy</SectionLabel>
        <Group>
          <Row icon="shield" label="Local-only history" sub="Tags never leave this device" value={true}/>
          <Row icon="trash" label="Clear scan history" />
        </Group>

        <SectionLabel t={t}>About</SectionLabel>
        <Group>
          <Row icon="globe" label="Help center"/>
          <Row icon="mail" label="Contact support"/>
          <Row icon="card" label="Version" value="2.4.1 (build 318)"/>
        </Group>

        <div style={{ padding: '20px 0 32px', textAlign: 'center', color: t.textDim, fontSize: 11 }}>
          NFC All-in-One · Made with ❤︎ for tinkerers
        </div>
      </Screen>
    );
  };

  // ─────────────────────────────────────────────────────────
  // 9) TAG DETAIL — saved tag inspector (compact alt of result)
  // ─────────────────────────────────────────────────────────
  const TagDetail = ({ t }) => (
    <Screen t={t}>
      <TopBar t={t} title="Tag" leading="arrow-left" trailing="overflow"/>

      {/* Hero */}
      <div style={{ padding: '0 20px' }}>
        <div style={{
          background: `linear-gradient(160deg, ${t.accent}, ${t.accentDeep})`,
          color: t.accentInk, borderRadius: 28, padding: '20px 22px',
          position: 'relative', overflow: 'hidden',
        }}>
          <div style={{
            position: 'absolute', right: -40, bottom: -40, width: 200, height: 200,
            borderRadius: '50%', border: `1px solid ${t.accentInk}25`,
          }}/>
          <div style={{
            position: 'absolute', right: -10, bottom: -10, width: 140, height: 140,
            borderRadius: '50%', border: `1px solid ${t.accentInk}25`,
          }}/>
          <div style={{ fontSize: 11, fontWeight: 600, letterSpacing: 1, textTransform: 'uppercase', opacity: 0.7 }}>Saved tag</div>
          <div style={{ fontSize: 26, fontWeight: 500, marginTop: 8, letterSpacing: -0.4, lineHeight: 1.1 }}>
            apartment-key
          </div>
          <div style={{ fontSize: 13, marginTop: 6, opacity: 0.85, fontFamily: 'ui-monospace, Menlo, monospace' }}>
            04:A2:9F:E2:6C:51:80
          </div>
          <div style={{ display: 'flex', gap: 8, marginTop: 14 }}>
            <div style={{ background: 'rgba(0,0,0,0.18)', borderRadius: 999, padding: '4px 10px', fontSize: 11, fontWeight: 500 }}>NTAG215</div>
            <div style={{ background: 'rgba(0,0,0,0.18)', borderRadius: 999, padding: '4px 10px', fontSize: 11, fontWeight: 500 }}>URL · NDEF</div>
            <div style={{ background: 'rgba(0,0,0,0.18)', borderRadius: 999, padding: '4px 10px', fontSize: 11, fontWeight: 500 }}>137 B</div>
          </div>
        </div>
      </div>

      {/* Quick actions */}
      <div style={{ padding: '14px 20px', display: 'grid', gridTemplateColumns: 'repeat(4, 1fr)', gap: 10 }}>
        {[
          ['copy', 'Copy'],
          ['share', 'Share'],
          ['clone', 'Clone'],
          ['trash', 'Delete'],
        ].map(([i, l]) => (
          <div key={i} style={{
            background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 16,
            padding: '14px 0', display: 'flex', flexDirection: 'column', alignItems: 'center', gap: 6,
            color: i === 'trash' ? t.danger : t.text,
          }}>
            <Icon name={i} size={18}/>
            <div style={{ fontSize: 11, fontWeight: 500 }}>{l}</div>
          </div>
        ))}
      </div>

      <SectionLabel t={t}>Payload</SectionLabel>
      <div style={{ padding: '0 20px' }}>
        <div style={{
          background: t.surfaceCard, border: `1px solid ${t.border}`, borderRadius: 16,
          padding: '14px 16px',
        }}>
          <div style={{ fontSize: 11, color: t.textMuted, fontWeight: 600, letterSpacing: 0.6 }}>URL</div>
          <div style={{ fontFamily: 'ui-monospace, Menlo, monospace', fontSize: 13, marginTop: 4, wordBreak: 'break-all' }}>
            https://nfc-allinone.app/p/8d2a9?room=204
          </div>
        </div>
      </div>

      <SectionLabel t={t}>Activity</SectionLabel>
      <div style={{ padding: '0 20px 24px', display: 'flex', flexDirection: 'column', gap: 0 }}>
        {[
          ['Scanned', '2 min ago'],
          ['Cloned to NTAG215', 'Apr 28'],
          ['Created', 'Mar 12, 2026'],
        ].map(([k, v], i) => (
          <div key={i} style={{ display: 'flex', alignItems: 'center', gap: 12, padding: '10px 0', borderBottom: i < 2 ? `1px solid ${t.border}` : 'none' }}>
            <div style={{ width: 8, height: 8, borderRadius: '50%', background: i === 0 ? t.accent : t.borderStrong }}/>
            <div style={{ flex: 1, fontSize: 13 }}>{k}</div>
            <div style={{ fontSize: 12, color: t.textMuted }}>{v}</div>
          </div>
        ))}
      </div>
    </Screen>
  );

  return {
    Onboarding, Home, ReadScanning, ReadResult, Write, Clone, History, Settings, TagDetail,
    T, Icon,
  };
})();

Object.assign(window, { NFC });
